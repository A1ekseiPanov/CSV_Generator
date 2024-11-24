package ru.panov.util;

import ru.panov.exceptions.FileWriteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Класс для записи данных в формате CSV в файл.
 *
 * @param <T> Тип данных, которые будут конвертироваться в CSV и записываться в файл.
 */
public class CSVWriter<T> {
    CSVConverter<T> converter = new CSVConverter<>();

    /**
     * Записывает список объектов в файл в формате CSV.
     *
     * @param data Список объектов, которые будут записаны в файл.
     * @param path Путь к файлу, в который будут записаны данные.
     * @throws FileWriteException Если произошла ошибка при записи в файл.
     */
    public void writeToFile(List<T> data, String path) throws FileWriteException {
        Path p = Path.of(path);
        try {
            if (p.getParent() != null && !Files.exists(p.getParent())) {
                Files.createDirectories(p.getParent());
            }

            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            List<String> content = converter.objectListConvertToCSV(data);
            Files.write(p, content);
        } catch (IOException e) {
            throw new FileWriteException("Проблемы при записи в файл");
        }
    }
}