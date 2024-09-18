package ru.panov.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVGeneratorRepository implements GeneratorRepository {

    @Override
    public void writeToFile(String path, List<String> data) {
        Path p = Path.of(path);
        try {
            Files.createDirectories(Path.of(path).getParent());

            if (!Files.exists(p)) {
                Files.createFile(p);
            }

            Files.write(p, data);
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при записи в файл");
        }
    }
}