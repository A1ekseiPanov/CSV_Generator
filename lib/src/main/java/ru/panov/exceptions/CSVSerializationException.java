package ru.panov.exceptions;

/**
 * Исключение, выбрасываемое при ошибках сериализации в формат CSV.
 * <p>
 * Класс {@code CSVSerializationException} расширяет {@link RuntimeException}
 * и используется для указания на проблемы, возникающие во время попытки
 * сериализации объекта в CSV, например, если класс не имеет необходимой
 * аннотации {@code @CSV}.
 * </p>
 */
public class CSVSerializationException extends RuntimeException {
    public CSVSerializationException(String message) {
        super(message);
    }
}