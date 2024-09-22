package ru.panov;

import ru.panov.model.Cat;
import ru.panov.model.User;
import ru.panov.repository.GeneratorRepository;
import ru.panov.repository.GeneratorRepositoryImpl;
import ru.panov.service.CsvGeneratorServiceImpl;
import ru.panov.service.GeneratorService;
import ru.panov.util.CSVWriter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.panov.util.Constants.PATH_FILE;

public class Main {
    public static void main(String[] args) {
        GeneratorRepository generatorRepository = new GeneratorRepositoryImpl();
        CSVWriter<User> userCSVWriter = new CSVWriter<>();
        GeneratorService<User> generatorService =
                new CsvGeneratorServiceImpl<>(generatorRepository, userCSVWriter);
        Cat cat = new Cat("Барсик", 2);
        Cat cat2 = new Cat("Ба111", 233);

        User user1 = new User(1L, "Илон", "Маск",
                LocalDate.of(1992, 12, 14), List.of(cat,cat2),Set.of(1,2), Map.of("key1", 1, "key2", 2));
        User user2 = new User(1L, "Илон", "Маск",
                LocalDate.of(1992, 12, 14), List.of(cat),Set.of(1,2,3), Map.of("key1", 122, "key2", 22));

        List<User> users = List.of(user1, user2);
        generatorService.writeToFile(PATH_FILE, users);
    }
}