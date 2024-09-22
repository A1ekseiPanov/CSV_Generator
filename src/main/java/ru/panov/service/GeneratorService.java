package ru.panov.service;

import java.util.List;

public interface GeneratorService<T> {
    void writeToFile(String path, List<T> objectList);
}