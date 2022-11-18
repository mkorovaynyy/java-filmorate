package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.customException.FilmIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.servise.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@RestController
public class FilmController {
    @Getter
    private final InMemoryFilmStorage inMemoryFilmStorage;
    private final FilmService filmService;

    @Autowired
    public FilmController(InMemoryFilmStorage inMemoryFilmStorage, FilmService filmService) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
        this.filmService = filmService;
    }

    //добавление фильма
    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) {
        return inMemoryFilmStorage.create(film);
    }

    //обновление фильма
    @PutMapping("/films")
    public Film update(@Valid @RequestBody Film film) {
        return inMemoryFilmStorage.update(film);
    }
    //получение всех фильмов
    @GetMapping("/films")
    public Collection<Film> getAll() {
        return inMemoryFilmStorage.getAll();
    }

    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable String id) {
        if(inMemoryFilmStorage.getFilmHashMap().containsKey(Integer.parseInt(id))) {
            return inMemoryFilmStorage.getFilmHashMap().get(Integer.parseInt(id));
        } else throw new FilmIdException("фильм с указанным id отсутствует");
    }

    //пользователь ставит лайк фильму
    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable String id, @PathVariable String userId) {
    filmService.addLike(inMemoryFilmStorage, Integer.parseInt(id), Integer.parseInt(userId));
    }

    //пользователь удаляет лайк.
    @DeleteMapping("/films/{id}/like/{userId}")
    public void removeLike(@PathVariable String id, @PathVariable String userId) {
        filmService.removeLike(inMemoryFilmStorage, Integer.parseInt(id), Integer.parseInt(userId));
    }

    //возвращает список из первых count фильмов по количеству лайков.
    //Если значение параметра count не задано, верните первые 10
    @GetMapping("/films/popular")
    public Collection<Film> topFilms(@RequestParam (defaultValue = "10")String count) {
        return filmService.topFilms(inMemoryFilmStorage, Integer.parseInt(count));
    }
}
