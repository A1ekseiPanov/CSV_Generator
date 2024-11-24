package ru.panov.model;

import ru.panov.annotations.CSV;
import ru.panov.annotations.Column;
import ru.panov.annotations.Lazy;
import ru.panov.annotations.Transient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CSV
public class User {
    private final Long id;
    @Column(name = "Имя")
    private final String firstName;
    @Column(name = "Фамилия")
    private final String lastName;
    @Column(name = "День рождения")
    private final LocalDate birthday;
    @Lazy
    private final List<Cat> cats;
    @Transient
    private final Set<Integer> set;
    private final Map<String, Integer> map;

    public User(Long id, String firstName, String lastName, LocalDate birthday,
                List<Cat> cats, Set<Integer> set, Map<String, Integer> map) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.cats = cats;
        this.set = set;
        this.map = map;
    }
}