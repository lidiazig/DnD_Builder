package com.lidia.ddbuilder.pojo;

public class Token {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUser(){
        return Integer.parseInt(token);
    }
}
