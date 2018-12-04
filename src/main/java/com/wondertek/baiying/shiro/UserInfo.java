package com.wondertek.baiying.shiro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wd on 2017/10/11.
 * 既然需要进行身份权限控制，那么少不了创建用户实体类，权限实体类。
 * 在权限管理系统中，有这么几个角色很重要，这个要是不清楚的话，那么就很难理解，我们为什么这么编码了。
 * 第一是用户表：在用户表中保存了用户的基本信息，账号、密码、姓名，性别等；
 * 第二是：权限表（资源+控制权限）：这个表中主要是保存了用户的URL地址，权限信息；
 * 第三就是角色表：在这个表重要保存了系统存在的角色；
 * 第四就是关联表：用户-角色管理表（用户在系统中都有什么角色，比如admin，vip等），角色-权限关联表（每个角色都有什么权限可以进行操作
 * 依据这个理论，我们进行来进行编码，很明显的我们第一步就是要进行实体类的创建。在这里我们使用Mysql和JPA进行操作数据库。
 * 新建实体类UserInfo、SysRole、SysPermission（采用逆向工程生成数据库表，然后导入数据）--非关键的get、set方法省略
 */
@Entity
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue
    private long uid;// 用户id

    @Column(unique= true)
    private String username;// 帐号

    private String name;// 名称（昵称或者真实姓名，不同系统不同定义）

    private String password; // 密码;
    private String salt;// 加密密码的盐

    private byte state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
    // 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch= FetchType.EAGER) // 立即从数据库中进行加载数据
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
            @JoinColumn(name = "roleId") })
    private List<SysRole> roleList;// 一个用户具有多个角色

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}
