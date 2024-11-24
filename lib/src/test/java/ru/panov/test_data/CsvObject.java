package ru.panov.test_data;

import ru.panov.annotations.CSV;

@CSV
public class CsvObject {

    private final String name;
    private final String type;

    public CsvObject(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
