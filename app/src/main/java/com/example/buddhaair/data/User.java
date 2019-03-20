package com.example.buddhaair.data;

public class User {
    private int id;
    private String name,username,email,password;
    private boolean error;

    public User(int id, String name, String username, String email, String password, boolean error) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isError() {
        return error;
    }
}
