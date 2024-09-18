package ru.panov;

import ru.panov.model.User;
import ru.panov.repository.CSVGeneratorRepository;
import ru.panov.repository.GeneratorRepository;
import ru.panov.service.CsvGeneratorServiceImpl;
import ru.panov.service.GeneratorService;

import java.util.List;

import static ru.panov.util.Constants.PATH_FILE;

public class Main {
    public static void main(String[] args) {
        GeneratorRepository generatorRepository = new CSVGeneratorRepository();
        GeneratorService generatorService = new CsvGeneratorServiceImpl(generatorRepository);

        User user1 = new User(1L, "Илон", "Маск", "elon_mask@yandex.ru");
        User user2 = new User(1L, "Илон", "Маск", "elon_mask@yandex.ru");
        User user3 = new User(1L, "Илон", "Маск", "elon_mask@yandex.ru");
        User user4 = new User(1L, "Илон", "Маск", "elon_mask@yandex.ru");
        User user5 = new User(1L, "Илон", "Маск", "elon_mask@yandex.ru");
        List<Object> users = List.of(user1, user2, user3, user4, user5);

        generatorService.writeToFile(PATH_FILE, users);
    }
}