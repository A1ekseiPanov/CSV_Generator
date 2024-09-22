package ru.panov.model;

import ru.panov.annotations.Column;

import java.io.Serializable;

public class Cat implements Serializable {
    @Column(name = "Имя кошки")
    private final String name;
    @Column(name = " Возрост кошки")
    private final int age;


    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
