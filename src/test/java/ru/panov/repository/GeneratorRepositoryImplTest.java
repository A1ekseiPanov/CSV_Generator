package ru.panov.repository;

import org.junit.jupiter.api.*;
import ru.panov.exceptions.FileWriteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.panov.util.Constants.PATH_FILE;

class GeneratorRepositoryImplTest {
    private static GeneratorRepository repository;

    @BeforeAll
    static void beforeAll() {
        repository = new GeneratorRepositoryImpl();
    }

    @AfterEach
    void tearDown() throws IOException {
        if (Files.exists(Path.of(PATH_FILE))) {
            Files.delete(Path.of(PATH_FILE));
        }
    }

    @Test
    @DisplayName("Запись в файл, успешно")
    void writeToFile() throws IOException {
        List<String> strings = List.of("String1", "String2", "String3");

        repository.writeToFile(PATH_FILE, strings);

        List<String> fileStrings = Files.readAllLines(Path.of(PATH_FILE));
        assertEquals(strings, fileStrings);
        assertEquals(strings.size(), fileStrings.size());
    }

    @Test
    @DisplayName("Выброс исключения при попытке записи в файл с неверным путем")
    void writeToFile_ExceptionWhenWritingToFile() {
        List<String> strings = List.of("String4", "String5", "String6");

        assertThrows(FileWriteException.class, () -> repository.writeToFile("", strings),
                "Проблемы при записи в файл");
    }
}