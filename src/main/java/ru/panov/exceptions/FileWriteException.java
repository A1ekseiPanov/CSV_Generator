package ru.panov.exceptions;

/**
 * Исключение, выбрасываемое при ошибках записи в файл.
 * <p>
 * Класс {@code FileWriteException} расширяет {@link RuntimeException}
 * и используется для указания на проблемы, возникающие во время
 * записи данных в файл.
 * </p>
 */
public class FileWriteException extends RuntimeException {
    public FileWriteException(String message) {
        super(message);
    }
}