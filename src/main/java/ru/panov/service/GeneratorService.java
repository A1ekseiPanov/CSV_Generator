package ru.panov.service;

import java.util.List;

/**
 * Интерфейс для генерации вывода в файл.
 *
 * @param <T> тип обрабатываемых объектов
 */
public interface GeneratorService<T> {

    /**
     * Записывает список объектов в указанный файл.
     *
     * @param path       путь к файлу, в который будут записаны данные
     * @param objectList список объектов, которые будут записаны в файл
     */
    void writeToFile(String path, List<T> objectList);
}