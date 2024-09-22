package ru.panov.service;

import ru.panov.repository.GeneratorRepository;
import ru.panov.util.ObjectHandler;

import java.util.List;

public class CsvGeneratorServiceImpl<T> implements GeneratorService<T> {
    private final GeneratorRepository repository;
    private final ObjectHandler<T>objectHandler;

    public CsvGeneratorServiceImpl(GeneratorRepository repository,
                                   ObjectHandler<T> objectHandler) {
        this.repository = repository;
        this.objectHandler = objectHandler;
    }

    public void writeToFile(String path, List<T> data) {

        List<String> stringDataList = objectHandler.objectToCsv(data);

        repository.writeToFile(path, stringDataList);
    }
}