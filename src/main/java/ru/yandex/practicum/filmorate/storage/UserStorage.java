package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {

    public User create(User user);

    public User update(User user);

    public Collection<User> getAllUsers();

    public void validate(User user);

    public Integer generateId();
}
