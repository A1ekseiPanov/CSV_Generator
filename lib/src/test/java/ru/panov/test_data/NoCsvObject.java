package ru.panov.test_data;

import ru.panov.annotations.Column;

public class NoCsvObject {
    @Column(name = "Имя")
    private final String name;

    public NoCsvObject(String name) {
        this.name = name;
    }
}