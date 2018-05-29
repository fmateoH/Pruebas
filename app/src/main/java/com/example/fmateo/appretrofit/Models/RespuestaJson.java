package com.example.fmateo.appretrofit.Models;

import java.lang.reflect.Array;

public class RespuestaJson {
    private boolean isSuccess;
    private String success;
    private Data data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
