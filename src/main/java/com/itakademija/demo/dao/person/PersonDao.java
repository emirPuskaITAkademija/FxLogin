package com.itakademija.demo.dao.person;

import com.itakademija.demo.dao.connection.ConnectionPool;
import com.itakademija.demo.dao.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements Dao<Person> {
    @Override
    public Person save(Person person) {
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
    public List<Person> findAll() {
        ConnectionPool pool = ConnectionPool.instance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM person";
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareCall(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                Date birthday = resultSet.getDate("birthday");
                person.setBirthday(birthday.toLocalDate());
                Gender gender = resultSet.getString("gender").equalsIgnoreCase("male") ? Gender.MALE : Gender.FEMALE;
                person.setGender(gender);

                persons.add(person);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        pool.releaseConnection(connection);
        return persons;
    }
}
