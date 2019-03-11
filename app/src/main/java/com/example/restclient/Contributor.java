package com.example.restclient;

public class Contributor {
    public String login;
    public String html_url;

    public int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}
