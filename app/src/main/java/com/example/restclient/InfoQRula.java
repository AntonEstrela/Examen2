package com.example.restclient;

class InfoQRula {
    private static final InfoQRula ourInstance = new InfoQRula();

    public static InfoQRula getInstance() {
        return ourInstance;
    }
    public String name;

    private InfoQRula() {
    }
}
