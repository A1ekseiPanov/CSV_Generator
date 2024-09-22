package ru.panov.repository;

import java.util.List;

/**
 * Интерфейс для репозитория, отвечающего за запись данных в файл.
 * <p>
 * Этот интерфейс определяет методы для записи содержимого в файл.
 * </p>
 */
public interface GeneratorRepository {
    /**
     * Записывает содержимое в файл по указанному пути.
     *
     * @param path    Путь к файлу, в который будет записано содержимое.
     * @param content Содержимое, которое нужно записать.
     */
    void writeToFile(String path, List<String> content);
}