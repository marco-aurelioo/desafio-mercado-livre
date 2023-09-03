package com.dev.mercadolivre.model;

import org.springframework.context.ApplicationEvent;

public class EmailEventModel extends ApplicationEvent {

    private String msg;
    private String email;

    public EmailEventModel(Object source, String msg, String email) {
        super(source);
        this.msg = msg;
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
