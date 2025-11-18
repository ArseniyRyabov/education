package com.github.arseniyryabov.education.controller.model;

public class UserResponse {
    private String userName;
    private String lastName;
    private String secondName;


    public UserResponse() {
    }

    public UserResponse(String userName, String lastName, String secondName) {
        this.userName = userName;
        this.lastName = lastName;
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
