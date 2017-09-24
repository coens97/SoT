package com.coen.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StandardResult {
    private boolean succes;
    private String message;


    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StandardResult(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }

    public StandardResult() {


    }
}
