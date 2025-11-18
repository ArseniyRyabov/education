package com.github.arseniyryabov.education.controller.model;


public class UserCreatingRequest {

    private String lastName;
    private String userName;
    private String secondName;


    public UserCreatingRequest(String lastName, String userName, String secondName) {
        this.lastName = lastName;
        this.userName = userName;
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

    public void setUserName(String name) {
        this.userName = userName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
