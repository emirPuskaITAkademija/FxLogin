package com.itakademija.demo.gui;

import com.itakademija.demo.dao.Dao;
import com.itakademija.demo.dao.connection.ConnectionPool;
import com.itakademija.demo.dao.person.Person;
import com.itakademija.demo.dao.person.PersonDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class PersonAdapterDao implements Dao<Person> {

    private final PersonDao personDao = new PersonDao();

    @Override
    public Person save(Person person) {
        String sqlInsert = """
            INSERT INTO person 
            (name, surname, birthday, gender)
            VALUES (?, ?, ?, ?)
            """;
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            LocalDate birthday = person.getBirthday();
            preparedStatement.setDate(3, Date.valueOf(birthday));
            preparedStatement.setString(4, person.getGender().name());
            preparedStatement.executeUpdate();

            // Retrieving the generated key
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    person.setId(id);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null; // Return null in case of an error
        } finally {
            pool.releaseConnection(connection);
        }
        return person;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public ObservableList<Person> findAll(){
        List<Person> persons = personDao.findAll();
        return FXCollections.observableArrayList(persons);
    }
}
