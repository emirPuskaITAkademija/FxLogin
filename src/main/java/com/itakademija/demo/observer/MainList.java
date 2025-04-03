package com.itakademija.demo.observer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Scanner;

/**
 * Nagradni fond koji zavisi od broja igrača koji će igrati u mom programu.
 * Kako raste broj igrača ažurira se prikaz povećanja fonda.
 */
public class MainList {
    public static void main(String[] args) {
        ObservableList<String> players = FXCollections.observableArrayList();
        players.addListener(MainList::onLutrijaFondChangeListener);
        players.addListener(new PlayerListChangeListener());
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("Enter player name or 'exit' to exit: ");
            while(scanner.hasNextLine()){
                String playerName = scanner.nextLine();
                if(playerName.equalsIgnoreCase("exit")){
                    break;
                }
                players.add(playerName);
            }
        }
    }

    private static void onLutrijaFondChangeListener(ListChangeListener.Change<? extends String> c) {
        ObservableList<? extends String> list = c.getList();
        double nagradniFond = list.size() * 5.0;
        System.out.printf("Lutrija BiH : Nagradni fond %s%n", nagradniFond);
    }

    private static class PlayerListChangeListener implements ListChangeListener<String> {
        @Override
        public void onChanged(Change<? extends String> c) {
            ObservableList<? extends String> list = c.getList();
            double nagradniFond = list.size() * 2.0;
            System.out.printf("Nagradni fond %s%n", nagradniFond);
        }
    }
}
