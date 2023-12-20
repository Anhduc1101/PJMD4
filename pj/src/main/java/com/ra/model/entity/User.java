package com.ra.model.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    private int userId;
//    @NotEmpty(message = "Can not be blank")
    private String userName;
    @Email(message = "Incorrect email format")
    @NotEmpty(message = "Can not be blank")
    private String email;
    @NotEmpty(message = "Can not be blank")
    private String password;
//    @NotEmpty(message = "Can not be blank and must input 10 numbers")
    private String phone;
    private boolean role;
    private boolean status;

    public User() {
    }

    public User(int userId, String userName, String email, String password,String phone, boolean role, boolean status) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
