package ru.panov.service;

import java.util.List;

public interface GeneratorService {
    void writeToFile(String path, List<Object> objectList);
}