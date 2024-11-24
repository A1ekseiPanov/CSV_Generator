package ru.panov.exceptions;

/**
 * Исключение, выбрасываемое при ошибках доступа к полям.
 * <p>
 * Класс {@code FieldAccessException} расширяет {@link RuntimeException}
 * и используется для указания на проблемы, возникающие при попытке доступа
 * к полям объекта.
 * </p>
 */
public class FieldAccessException extends RuntimeException {
    public FieldAccessException(String message) {
        super(message);
    }
}