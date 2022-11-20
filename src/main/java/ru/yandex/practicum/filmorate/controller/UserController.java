package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.servise.UserService;

import javax.validation.Valid;
import java.util.Collection;



@Slf4j
@RestController
public class UserController {
    @Getter
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //создание пользователя
    @PostMapping("/users")
    public User create(@Valid @RequestBody User user) {
        return userService.getInMemoryUserStorage().create(user);
    }
    //обновление пользователя
    @PutMapping("/users")
    public User update(@Valid @RequestBody User user) {
        return userService.getInMemoryUserStorage().update(user);
    }
    //получение списка всех пользователей
    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return userService.getInMemoryUserStorage().getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(Integer.parseInt(id));
    }

    //добавление в друзья
    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable String friendId, @PathVariable String id) {
        userService.addFriend(Integer.parseInt(id), Integer.parseInt(friendId));
    }

    //удаление из друзей.
    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void removeFriend(@PathVariable String friendId, @PathVariable String id) {
        userService.removeFriend(Integer.parseInt(id), Integer.parseInt(friendId));
    }

    //возвращаем список пользователей, являющихся его друзьями
    @GetMapping("/users/{id}/friends")
    public Collection<User> listOfFriends(@PathVariable String id) {

        return userService.retListOfFriends(Integer.parseInt(id));
    }

    //список друзей, общих с другим пользователем
    @GetMapping("/users/{id}/friends/common/{otherId}")
    public  Collection<User> listOfCommonFriends(@PathVariable String id, @PathVariable String otherId) {
        return userService.retListOfCommonFriends(Integer.parseInt(id), Integer.parseInt(otherId));
    }
}
