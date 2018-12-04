<?xml version="1.0" encoding="UTF-8"?>
<xml>
    <userList>
    <#list userList as user>
        <user>
            <userName>${user.userName!}</userName>
            <passWord>${user.passWord!}</passWord>
            <realName>${user.realName!}</realName>
            <age>${user.age!}</age>
            <addr>${user.addr!}</addr>
        </user>
    </#list>
    </userList>
</xml>