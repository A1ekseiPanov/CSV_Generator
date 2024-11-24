package ru.panov.test_data;

import ru.panov.annotations.CSV;
import ru.panov.annotations.Column;
import ru.panov.annotations.Lazy;
import ru.panov.annotations.Transient;

import java.util.List;
import java.util.Map;

@CSV
public class TestObject {
    @Column(name = "ID")
    private final int id;

    @Column(name = "Имя")
    private final String name;

    @Transient
    private final String secretField;

    @Lazy
    private final String lazyField;

    @Column(name = "Вложенный лист")
    private final List<String> nestedList;

    @Column(name = "Вложенная мапа")
    private final Map<String, Integer> nestedMap;

    public TestObject(int id, String name, String secretField, String lazyField,
                      List<String> nestedList, Map<String, Integer> nestedMap) {
        this.id = id;
        this.name = name;
        this.secretField = secretField;
        this.lazyField = lazyField;
        this.nestedList = nestedList;
        this.nestedMap = nestedMap;
    }
}