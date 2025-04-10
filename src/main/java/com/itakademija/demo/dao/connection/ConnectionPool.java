package com.itakademija.demo.dao.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool instance;
    private final static int maxConnections = 10;

    private final List<Connection> availableConnections = new ArrayList<>();
    private final List<Connection> inUseConnections = new ArrayList<>();

    private ConnectionPool() {
        for (int i = 0; i < maxConnections; i++) {
            Connection connection = createConnection();
            availableConnections.add(connection);
        }
    }

    private Connection createConnection() {
        try{
            String url = ConnectionProperty.URL.get();
            String username = ConnectionProperty.USERNAME.get();
            String password = ConnectionProperty.PASSWORD.get();
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        if(availableConnections.isEmpty()){
            throw new RuntimeException("No connections available");
        }
        Connection connection = availableConnections.get(availableConnections.size() - 1);
        availableConnections.remove(connection);
        inUseConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if(connection == null){
            return false;
        }
        availableConnections.add(connection);
        inUseConnections.remove(connection);
        return true;
    }

    public int getAvailableConnectionsCount() {
        return availableConnections.size();
    }

    public static ConnectionPool instance() {
        if(instance == null){
            instance = new ConnectionPool();
        }
        return instance;
    }
}

