package ru.panov.service;

import ru.panov.repository.GeneratorRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.panov.util.Constants.DELIMITER;

public class CsvGeneratorServiceImpl implements GeneratorService {
    private final GeneratorRepository repository;

    public CsvGeneratorServiceImpl(GeneratorRepository repository) {
        this.repository = repository;
    }

    public void writeToFile(String path, List<Object> objectList) {
        List<String> data = new ArrayList<>();

        if (objectList == null || objectList.isEmpty()) {
            return;
        }
        data.add(getHeader(objectList.get(0)));

        data.addAll(objectList.stream()
                .map(this::getValues)
                .toList());

        repository.writeToFile(path, data);
    }

    private String getHeader(Object o) {
        return String.join(DELIMITER, getFieldNames(o));
    }

    private String getValues(Object o) {
        return String.join(DELIMITER, getFieldValues(o));
    }

    private List<String> getFieldNames(Object o) {
        return Arrays.stream(getFields(o))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    private List<String> getFieldValues(Object o) {
        return Arrays.stream(getFields(o))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(o);
                        return value != null ? value.toString() : "";
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Проблемы при получении значения поля");
                    }
                })
                .collect(Collectors.toList());
    }

    private Field[] getFields(Object o) {
        return o.getClass().getDeclaredFields();
    }
}