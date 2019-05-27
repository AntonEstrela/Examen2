package com.example.restclient;

public class User {
    String login;
    String avatar_url;

    int public_repos;
    int following;

    @Override
    public String toString() {
        return login;
    }
}
