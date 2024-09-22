package ru.panov;

import ru.panov.model.Cat;
import ru.panov.model.User;
import ru.panov.repository.GeneratorRepositoryImpl;
import ru.panov.repository.GeneratorRepository;
import ru.panov.service.CsvGeneratorServiceImpl;
import ru.panov.service.GeneratorService;
import ru.panov.util.ObjectHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.panov.util.Constants.PATH_FILE;

public class Main {
    public static void main(String[] args) {
        GeneratorRepository generatorRepository = new GeneratorRepositoryImpl();
        ObjectHandler<User> userObjectHandler = new ObjectHandler<>();
        GeneratorService<User> generatorService =
                new CsvGeneratorServiceImpl<>(generatorRepository, userObjectHandler);
        Cat cat = new Cat("Барсик", 2);
        Cat cat2 = new Cat("Барсик", 2);


        User user1 = new User(1L, "Илон", "Маск",
                LocalDate.of(1992, 12, 14), List.of(cat,cat2),Set.of(1,2), Map.of("s",cat,"SSS",cat2));
        User user2 = new User(1L, "Илон", "Маск",
                LocalDate.of(1992, 12, 14), List.of(cat),Set.of(1,2,3),  Map.of("s",cat2));
//        User user2 = new User(1L, "Илон", "Маск",
//                LocalDate.of(1992, 12, 14));
//        User user3 = new User(1L, "Илон", "Маск",
//                LocalDate.of(1992, 12, 14));
//        User user4 = new User(1L, "Илон", "Маск",
//                LocalDate.of(1992, 12, 14));
//        User user5 = new User(1L, "Илон", "Маск",
//                LocalDate.of(1992, 12, 14));
        List<User> users = List.of(user1, user2);
//        generatorService.

//        try {
        generatorService.writeToFile(PATH_FILE, users);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }

    }
}