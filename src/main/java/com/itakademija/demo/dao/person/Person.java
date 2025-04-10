package com.itakademija.demo.dao.person;


import java.time.LocalDate;
import java.util.StringJoiner;

public class Person {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Gender gender;

    public Person() {
    }

    public Person(String name, String surname, LocalDate birthday, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("birthday=" + birthday)
                .add("gender=" + gender)
                .toString();
    }
}
