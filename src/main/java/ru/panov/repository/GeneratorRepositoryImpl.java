package ru.panov.repository;

import ru.panov.exceptions.FileWriteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Реализация интерфейса {@link GeneratorRepository}.
 * <p>
 * Класс {@code GeneratorRepositoryImpl} реализует методы для записи данных
 * в файл, создавая при необходимости директории и файл.
 * </p>
 */
public class GeneratorRepositoryImpl implements GeneratorRepository {
    @Override
    public void writeToFile(String path, List<String> data) {
        Path p = Path.of(path);
        try {
            if (p.getParent() != null && !Files.exists(p.getParent())) {
                Files.createDirectories(p.getParent());
            }

            if (!Files.exists(p)) {
                Files.createFile(p);
            }
            Files.write(p, data);
        } catch (IOException e) {
            throw new FileWriteException("Проблемы при записи в файл");
        }
    }
}