package com.github.arseniyryabov.education.controller.model;


public class UserCreatingRequest {

    private String lastName;
    private String name;
    private String secondName;


    public UserCreatingRequest() {}

    public UserCreatingRequest(String lastName, String name, String secondName) {
        this.lastName = lastName;
        this.name = name;
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
