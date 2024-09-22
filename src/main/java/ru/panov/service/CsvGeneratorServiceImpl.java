package ru.panov.service;

import ru.panov.repository.GeneratorRepository;
import ru.panov.util.CSVWriter;

import java.util.List;

/**
 * Реализация интерфейса {@link GeneratorService}, которая генерирует CSV-файлы.
 *
 * @param <T> тип обрабатываемых объектов
 */
public class CsvGeneratorServiceImpl<T> implements GeneratorService<T> {
    private final GeneratorRepository repository;
    private final CSVWriter<T> csvWriter;

    public CsvGeneratorServiceImpl(GeneratorRepository repository,
                                   CSVWriter<T> csvWriter) {
        this.repository = repository;
        this.csvWriter = csvWriter;
    }

    /**
     * Записывает указанный список объектов в CSV-файл по заданному пути.
     *
     * @param path путь к файлу, в который будут записаны CSV-данные
     * @param data список объектов, которые будут преобразованы в CSV и записаны в файл
     */
    public void writeToFile(String path, List<T> data) {

        List<String> stringDataList = csvWriter.objectToCsv(data);

        repository.writeToFile(path, stringDataList);
    }
}