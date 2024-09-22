package ru.panov.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.panov.exceptions.CSVSerializationException;
import ru.panov.test_data.NonCSVObject;
import ru.panov.test_data.TestObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVWriterTest {
    private CSVWriter<TestObject> csvWriter;
    private CSVWriter<NonCSVObject> nonCSVObjectCSVWriter;

    @BeforeEach
    void setUp() {
        csvWriter = new CSVWriter<>();
    }

    @Test
    @DisplayName("Преобразование объекта в CSV, успешно")
    void objectToCsv() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        TestObject testObject = new TestObject(1, "Пушкин А.С.", "secret", "lazy",
                Arrays.asList("A", "B"), map);
        List<TestObject> data = List.of(testObject);

        List<String> csvOutput = csvWriter.objectToCsv(data);

        assertNotNull(csvOutput);
        assertEquals(2, csvOutput.size());
        assertEquals("ID,Имя,lazyField,Вложенный лист,Вложенная мапа", csvOutput.get(0));
        assertEquals("1,Пушкин А.С., ,[ {A}; {B} ],[ {Key: key1; Value: 1}; {Key: key2; Value: 2} ]", csvOutput.get(1));
    }

    @Test
    @DisplayName("Выброс исключения при передаче пустого списка")
    void objectToCsv_ExceptionWithEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> csvWriter.objectToCsv(List.of()),
                "Входные данне пустые или null");
    }

    @Test
    @DisplayName("Выброс исключения для класса без аннотации @CSV")
    void objectToCsv_ExceptionForNonCSVAnnotatedClass() {
        nonCSVObjectCSVWriter = new CSVWriter<>();
        NonCSVObject nonCsvObject = new NonCSVObject("Test Name");
        List<NonCSVObject> data = List.of(nonCsvObject);

        assertThrows(CSVSerializationException.class, () -> nonCSVObjectCSVWriter.objectToCsv(data),
                "Класс NonCSVObject не может быть записан в CSV файл");
    }
}