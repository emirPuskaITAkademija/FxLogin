package com.itakademija.demo.observer;

import javafx.beans.property.SimpleIntegerProperty;

public class Person {

    private String name;
    private String surname;
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
}
