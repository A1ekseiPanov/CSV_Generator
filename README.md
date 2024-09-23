# CSV_Generator
Библиотека для генерации отчетов в CSV файл.
Проект реализует функционал конвертации объектов Java в CSV формат с поддержкой вложенных коллекций и карт. Используются кастомные аннотации для задания специфического поведения при сериализации объектов в CSV.

#### Основные возможности
- Конвертация объектов в CSV:
Использование аннотаций @CSV, @Column, @Lazy и @Transient для управления сериализацией объектов.
- Работа с вложенными структурами: Поддержка вложенных коллекций и карт.

#### Требования
- Java 15+
- Maven для управления зависимостями

##### Установка и запуск
- Склонируйте репозиторий:
Копировать код
git clone https://github.com/A1ekseiPanov/CSV_Generator.git
- Откройте проект в вашей среде разработки
- Найдите класс Main.java
- Произведите запуск

Для подключения библиотеки как зависимости добавить в pom.xml
```
<repositories>
    <repository>
        <id>CSV_Generator-mvn-repo</id>
        <url>https://github.com/A1ekseiPanov/CSV_Generator/raw/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>ru.panov</groupId>
        <artifactId>CSV_Generator</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```