package com.ra.model.entity;

import java.sql.Date;

public class Order {
    private int id;
    private User user;
    private int status;
    private String address;
    private Date create_at;

    public Order() {
    }

    public Order(int id, User user, int status, String address, Date create_at) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.address = address;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
