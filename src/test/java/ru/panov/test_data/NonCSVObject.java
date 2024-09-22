package ru.panov.test_data;

import ru.panov.annotations.Column;

public class NonCSVObject {
    @Column(name = "Имя")
    private final String name;

    public NonCSVObject(String name) {
        this.name = name;
    }
}