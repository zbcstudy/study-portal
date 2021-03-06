package com.wondertek.baiying.reflection;

public class User {
    private String userName;

    private String passWord;

    private String realName;

    private String addr;

    private String age;

    public User() {
    }

    public User(String userName, String passWord, String realName, String addr, String age) {
        this.userName = userName;
        this.passWord = passWord;
        this.realName = realName;
        this.addr = addr;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}