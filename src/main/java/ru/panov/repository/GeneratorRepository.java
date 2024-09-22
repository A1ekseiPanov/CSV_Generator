package ru.panov.repository;

import java.util.List;

public interface GeneratorRepository {
    void writeToFile(String path, List<String> content);
}