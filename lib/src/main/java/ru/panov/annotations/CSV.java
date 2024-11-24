package ru.panov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания, что класс может быть преобразован в формат CSV.
 * <p>
 * Аннотация {@code @CSV} применяется к классам, которые подлежат сериализации
 * в формат CSV.
 * </p>
 *
 * <h2>Пример использования:</h2>
 * <pre>{@code
 * @CSV
 * public class User {
 *     private int id;
 *     private String name;
 * }
 * }</pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSV {
}