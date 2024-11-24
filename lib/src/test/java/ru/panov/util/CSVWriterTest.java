package ru.panov.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.panov.exceptions.FileWriteException;
import ru.panov.test_data.CsvObject;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVWriterTest {
    private CSVWriter<CsvObject> csvWriter;

    @BeforeEach
    void setUp() {
        csvWriter = new CSVWriter<>();
    }

    @Test
    @DisplayName("Ошибка при записи в файл")
    void writeToFile_ShouldThrowFileWriteException() throws IOException {
        List<CsvObject> data = List.of(new CsvObject("John", "s"),
                new CsvObject("Jane", "ss"));

        assertThrows(FileWriteException.class, () -> csvWriter.writeToFile(data, ""),
                "Проблемы при записи в файл");
    }
}