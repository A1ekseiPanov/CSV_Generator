package ru.panov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания, что поле должно отображаться как пустое.
 * <p>
 * Аннотация {@code @Lazy} применяется к полям класса, которые не должны
 * отображаться при преобразовании объекта в формат
 * CSV.
 * </p>
 *
 * <h2>Пример использования:</h2>
 * <pre>{@code
 * public class User {
 *     private int id;
 *     @Lazy
 *     private String password; // Это поле будет отображаться как пустое
 *     private String name;
 * }
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Lazy {
}