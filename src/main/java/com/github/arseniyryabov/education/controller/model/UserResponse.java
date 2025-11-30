package com.github.arseniyryabov.education.controller.model;

public class UserResponse {
    private Long id;
    private String userName;
    private String lastName;
    private String secondName;


    public UserResponse(Long id, String userName, String lastName, String secondName) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.secondName = secondName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
