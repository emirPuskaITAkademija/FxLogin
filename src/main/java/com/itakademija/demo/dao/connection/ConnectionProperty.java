package com.itakademija.demo.dao.connection;


public enum ConnectionProperty {
    URL("jdbc:mysql://localhost:3308/sports"),
    USERNAME("root"),
    PASSWORD("root"),;

    private final String value;

    ConnectionProperty(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
