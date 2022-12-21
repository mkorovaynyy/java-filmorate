package ru.yandex.practicum.filmorate.storageInterfase;

import ru.yandex.practicum.filmorate.model.Genre;

import java.util.Collection;
import java.util.Optional;

public interface GenreStorage {
    Collection<Genre> findAll();
    Optional<Genre> getById(int id);
}