package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.customException.UserIdException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.servise.UserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
@Slf4j
@RestController
public class UserController {
    @Getter
    private final InMemoryUserStorage inMemoryUserStorage;

    private final UserService userService;
    @Autowired
    public UserController(InMemoryUserStorage inMemoryUserStorage, UserService userService) {
        this.userService = userService;
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    //создани пользователя
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        return inMemoryUserStorage.create(user);
    }

    //обновление пользователя
    @PutMapping("/users")
    public User update(@Valid @RequestBody User user) {
        return inMemoryUserStorage.update(user);
    }
    //получение списка всех пользователей
    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return inMemoryUserStorage.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        if(inMemoryUserStorage.getMapOfUser().containsKey(Integer.parseInt(id))) {
            return inMemoryUserStorage.getMapOfUser().get(Integer.parseInt(id));
        }
        else throw new UserIdException("пользователь с таким id отсутсвует");
    }

    //добавление в друзья
    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable String friendId, @PathVariable String id) {
        userService.addFriend(inMemoryUserStorage, Integer.parseInt(id), Integer.parseInt(friendId));
    }

    //удаление из друзей.
    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable String friendId, @PathVariable String id) {
        userService.removeFriend(inMemoryUserStorage, Integer.parseInt(id), Integer.parseInt(friendId));
    }

    //возвращаем список пользователей, являющихся его друзьями
    @GetMapping("/users/{id}/friends")
    public Collection<User> listOfFriends(@PathVariable String id) {
        return userService.retListOfFriends(inMemoryUserStorage, Integer.parseInt(id));
    }

    //список друзей, общих с другим пользователем
    @GetMapping("/users/{id}/friends/common/{otherId}")
    public  Collection<User> listOfCommonFriends(@PathVariable String id) {
        return userService.retListOfCommonFriends(inMemoryUserStorage, Integer.parseInt(id));
    }
}
