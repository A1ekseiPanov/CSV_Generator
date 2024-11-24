package ru.panov.model;

import ru.panov.annotations.Column;

public class Cat {
    @Column(name = "Имя кошки")
    private final String name;
    @Column(name = " Возрост кошки")
    private final int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}