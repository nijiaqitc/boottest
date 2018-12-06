package com.njq.boottest.controller;

import java.io.Serializable;

public class StoreResult implements Serializable {
    private static final long serialVersionUID = -454996210790887092L;
    private String url;
    private boolean isSuccess;

    public StoreResult() {
    }

    public String toString() {
        return "";
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }
}