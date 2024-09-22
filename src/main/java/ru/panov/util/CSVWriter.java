package ru.panov.util;

import ru.panov.annotations.CSV;
import ru.panov.annotations.Column;
import ru.panov.annotations.Lazy;
import ru.panov.annotations.Transient;
import ru.panov.exceptions.CSVSerializationException;
import ru.panov.exceptions.FieldAccessException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static ru.panov.util.Constants.DELIMITER;

/**
 * Класс для сериализации объектов в формат CSV.
 *
 * @param <T> тип объектов, которые будут сериализованы
 */
public class CSVWriter<T> {

    /**
     * Преобразует список объектов в формат CSV.
     *
     * @param data список объектов, которые будут преобразованы
     * @return список строк, представляющих объекты в формате CSV
     * @throws IllegalArgumentException  если входные данные пусты или равны null
     * @throws CSVSerializationException если класс объекта не имеет аннотации @CSV
     */
    public List<String> objectToCsv(List<T> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Входные данне пустые или null");
        }
        T d = data.get(0);

        if (!d.getClass().isAnnotationPresent(CSV.class)) {
            throw new CSVSerializationException("Класс %s не может быть записан в CSV файл, отсутствует аннотация над классом"
                    .formatted(d.getClass().getSimpleName()));
        }

        List<String> dataList = new ArrayList<>();

        dataList.add(Arrays.stream(getFields(d))
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .map(this::getFieldName)
                .collect(Collectors.joining(DELIMITER)));

        data.forEach(t -> {
            dataList.add(Arrays.stream(getFields(t))
                    .filter(field -> !field.isAnnotationPresent(Transient.class))
                    .map(field -> {
                        Object fieldValue = getFieldValue(field, t);
                        if (field.isAnnotationPresent(Lazy.class)) {
                            return " ";
                        }
                        if (this.isCollection(fieldValue)) {
                            Collection<?> list = (Collection<?>) fieldValue;
                            return this.nestedCollectionToString(list);
                        }
                        if (this.isMap(fieldValue)) {
                            Map<?, ?> map = (Map<?, ?>) fieldValue;
                            return this.nestedMapToString(map);
                        }
                        return fieldValue != null ? fieldValue.toString() : "";
                    }).collect(Collectors.joining(DELIMITER)));
        });
        return dataList;
    }

    private static <T> Field[] getFields(T t) {
        return t.getClass().getDeclaredFields();
    }

    private String getFieldName(Field field) {
        Column column = field.getAnnotation(Column.class);
        return column != null ? column.name() : field.getName();
    }

    private Object getFieldValue(Field field, Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new FieldAccessException("Не удалось получить значение поля " + field.getName());
        }
    }

    private String nestedCollectionToString(Collection<?> nestedCollection) {
        return nestedCollection.stream()
                .map(item -> {
                    if (isCollection(item)) {
                        return nestedCollectionToString((Collection<?>) item);
                    } else if (isMap(item)) {
                        return nestedMapToString((Map<?, ?>) item);
                    } else if (isStringOrPrimitive(item)) {
                        return "{" + item + "}";
                    } else {
                        return getCollect(item);
                    }
                })
                .collect(Collectors.joining("; ", "[ ", " ]"));  // Форматирование через точку с запятой
    }

    private String nestedMapToString(Map<?, ?> nestedMap) {
        return nestedMap.entrySet().stream()
                .map(entry -> {
                    Object key = entry.getKey();
                    Object value = entry.getValue();

                    String keyStr;
                    String valueStr;

                    keyStr = getString(key);
                    valueStr = getString(value);

                    return "{Key: " + keyStr + "; Value: " + valueStr + "}";
                })
                .collect(Collectors.joining("; ", "[ ", " ]"));  // Форматирование через точку с запятой
    }

    private String getString(Object value) {
        String valueStr;
        if (isCollection(value)) {
            valueStr = nestedCollectionToString((Collection<?>) value);
        } else if (isMap(value)) {
            valueStr = nestedMapToString((Map<?, ?>) value);
        } else if (isStringOrPrimitive(value)) {
            valueStr = value.toString();
        } else {
            valueStr = getCollect(value);
        }
        return valueStr;
    }

    private String getCollect(Object key) {
        return Arrays.stream(getFields(key))
                .map(f -> this.getFieldName(f) + " : " + this.getFieldValue(f, key))
                .collect(Collectors.joining(" ; ", "{ ", " }"));
    }

    private boolean isCollection(Object o) {
        return o instanceof Collection<?>;
    }

    private boolean isStringOrPrimitive(Object o) {
        return o instanceof String || WrapperUtil.isPrimitiveType(o);
    }

    private boolean isMap(Object o) {
        return o instanceof Map<?, ?>;
    }
}