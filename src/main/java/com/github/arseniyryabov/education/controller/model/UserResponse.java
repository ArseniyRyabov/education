package com.github.arseniyryabov.education.controller.model;

public class UserResponse {
    private String name;
    private String lastName;
    private String secondName;


    public UserResponse() {}

    public UserResponse(String name, String lastName, String secondName) {
        this.name = name;
        this.lastName = lastName;
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
