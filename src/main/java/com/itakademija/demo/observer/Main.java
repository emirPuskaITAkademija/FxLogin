package com.itakademija.demo.observer;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class Main {
    public static void main(String[] args) {
        Integer number = 23;
        SimpleIntegerProperty oneProperty = new SimpleIntegerProperty(23);
        SimpleIntegerProperty twoProperty = new SimpleIntegerProperty(12);

        NumberBinding suma = oneProperty.add(twoProperty); // 23 + 12
        suma.addListener(Main::onSumPropertyChanged);

        //oneProperty -> OBZERVIRANI property -  OBSERVER je blok koda funkcije onOnePropertyChanged
        oneProperty.addListener(Main::onOnePropertyChanged);
        oneProperty.addListener(Main::onOnePropertyChangedListener2);
        oneProperty.set(24);
        oneProperty.set(27);
        twoProperty.set(123);
    }

    private static void onOnePropertyChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.printf("Desila se promjena sa %s -> %s: IntegerNumberListener%n", oldValue, newValue);
    }

    private static void onOnePropertyChangedListener2(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.printf("Listener2: Desila se promjena sa %s -> %s: IntegerNumberListener%n", oldValue, newValue);
    }

    private static void onSumPropertyChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        System.out.printf("BINDER: Desila se promjena nad binderom %s -> %s: IntegerNumberListener%n", oldValue, newValue);

    }
}
