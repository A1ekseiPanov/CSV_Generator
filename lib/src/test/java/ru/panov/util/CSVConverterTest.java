package ru.panov.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.panov.exceptions.CSVSerializationException;
import ru.panov.test_data.NoCsvObject;
import ru.panov.test_data.TestObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVConverterTest {
    private CSVConverter<TestObject> csvConverter;
    private CSVConverter<NoCsvObject> nonCSVObjectCSVConverter;

    @BeforeEach
    void setUp() {
        csvConverter = new CSVConverter<>();
    }

    @Test
    @DisplayName("Преобразование объекта в CSV, успешно")
    void objectToCsv_objectListConvertToCSVSuccess() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        TestObject testObject = new TestObject(1, "Пушкин А.С.", "secret", "lazy",
                Arrays.asList("A", "B"), map);
        List<TestObject> data = List.of(testObject);

        List<String> csvOutput = csvConverter.objectListConvertToCSV(data);

        assertNotNull(csvOutput);
        assertEquals(2, csvOutput.size());
        assertEquals("ID,Имя,lazyField,Вложенный лист,Вложенная мапа", csvOutput.get(0));
        assertEquals("1,Пушкин А.С.,,[{A}; {B}],[{key1: 1}; {key2: 2}]", csvOutput.get(1));
    }

    @Test
    @DisplayName("Выброс исключения при передаче пустого списка")
    void objectToCsv_ExceptionWithEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> csvConverter.objectListConvertToCSV(List.of()),
                "Входные данне пустые или null");
    }

    @Test
    @DisplayName("Выброс исключения для класса без аннотации @CSV")
    void objectToCsv_ExceptionForNonCSVAnnotatedClass() {
        nonCSVObjectCSVConverter = new CSVConverter<>();
        NoCsvObject myTestObject = new NoCsvObject("Test Name");
        List<NoCsvObject> data = List.of(myTestObject);

        assertThrows(CSVSerializationException.class, () -> nonCSVObjectCSVConverter.objectListConvertToCSV(data),
                "Класс MyTestObject не может быть записан в CSV файл");
    }
}