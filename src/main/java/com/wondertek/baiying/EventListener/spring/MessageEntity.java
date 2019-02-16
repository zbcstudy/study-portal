package com.wondertek.baiying.EventListener.spring;

/**
 * Created by wd on 2018/12/29.
 */
public class MessageEntity {

    private String message;

    private String code;

    public MessageEntity() {
    }

    public MessageEntity(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
