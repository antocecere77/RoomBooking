package com.virtualpairprogrammers.roombooking.model;

import com.virtualpairprogrammers.roombooking.model.entities.User;

public class AngularUser {

    private Long id;
    private String name;

    public AngularUser() {
    }

    public AngularUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User asUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword("");
        return user;
    }
}
