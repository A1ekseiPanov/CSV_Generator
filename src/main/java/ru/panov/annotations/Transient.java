package ru.panov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания, что поле не участвует в преобразовании в формат CSV.
 * <p>
 * Аннотация {@code @Transient} применяется к полям класса, которые должны быть
 * исключены из процесса сериализации при преобразовании объекта в CSV. Поля,
 * помеченные этой аннотацией, не будут отображены в выходном файле CSV.
 * </p>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 * public class User {
 *     private int id;
 *
 *     @Transient
 *     private String password; // Это поле не будет включено в CSV
 *
 *     private String name;
 * }
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transient {
}