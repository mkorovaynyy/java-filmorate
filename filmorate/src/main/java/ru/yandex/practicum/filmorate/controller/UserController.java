package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.customExseption.CheckContainsException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@Slf4j
@ToString
public class UserController {

    @Getter
    private HashMap<Long, User> mapOfUser = new HashMap<>();

    //получение всех фильмов
    @GetMapping("/users")
    public String getFilms() {
        if(mapOfUser.isEmpty()) {
            return "Список пользователей пока пуст";
        } else return mapOfUser.toString();
    }

    //добавление фильма
    @PostMapping("/users")
    public User createFilm(@Valid @RequestBody User user) {
        validateUser(user);
        if(mapOfUser.containsKey(user.getId())) {
            log.debug("Поступил post запрос /film на создание объекта User: Создание невозможно - пользователь с указанным id " + user.getId() + " уже создан");
            throw new CheckContainsException("Создание невозможно - пользователь с указанным id " + user.getId() + " уже создан");
        }
        log.debug("Поступил post запрос /user на создание объекта User. Объект успешно создан");
        mapOfUser.put(user.getId(), user);
        return user;
    }

    //обновление фильма
    @PutMapping("/users")
    public User updateFilm(@Valid @RequestBody User user) {
        validateUser(user);
        if(!mapOfUser.containsKey(user.getId())) {
            log.debug("Поступил put запрос /user на обновление объекта User: обновление невозможно - пользователь с указанным id " + user.getId() + " отсутствует");
            throw new CheckContainsException("Обновление невозможно - пользователь с указанным id " + user.getId() + " отсутствует");
        }
        log.debug("Поступил put запрос /user на обновление объекта User. Объект успешно обновлен");
        mapOfUser.put(user.getId(), user);
        return user;
    }
    public void validateUser(User user) {
        if(user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }

}
