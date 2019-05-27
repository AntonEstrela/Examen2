package com.example.restclient;

public class Follower {
    String login;
    String avatar_url;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}
