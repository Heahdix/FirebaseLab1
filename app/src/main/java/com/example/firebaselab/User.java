package com.example.firebaselab;

public class User {
    public String login, password, id;

    public User(){

    }
    public User(String login, String password, String id){
        this.login = login;
        this.password = password;
        this.id = id;
    }
}
