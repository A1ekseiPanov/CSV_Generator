package ru.panov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания названия колонки при преобразовании поля объекта в CSV.
 * <p>
 * Аннотация {@code @Column} используется для задания имени столбца в файле CSV,
 * которое будет соответствовать полю класса.
 * </p>
 *
 * <h3>Пример использования:</h3>
 * <pre>{@code
 * public class User {
 *
 *   @Column(name = "Идентификатор пользователя")
 *   private int id;
 *
 *   @Column(name = "Имя пользователя")
 *   private String name;
 * }
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    /**
     * Задает название колонки в CSV, которое будет соответствовать аннотированному полю.
     *
     * @return Имя колонки.
     */
    String name();
}