package com.example.my_first_spring_boot.DTO;

public class LoginRequest {
    private String id;
    private String pass;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
