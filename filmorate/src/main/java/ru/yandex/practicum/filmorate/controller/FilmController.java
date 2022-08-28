package ru.yandex.practicum.filmorate.controller;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.customExseption.CheckContainsException;
import ru.yandex.practicum.filmorate.customExseption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@Slf4j
public class FilmController {

    @Getter
    private HashMap<Long, Film> mapOfFilms = new HashMap<>();

    //получение всех фильмов
    @GetMapping("/films")
    public String getFilms() {
        if(mapOfFilms.isEmpty()) {
            return "Список фильмов пока пуст";
        } else return mapOfFilms.toString();
    }

    //добавление фильма
    @PostMapping("/films")
    public Film createFilm(@Valid @RequestBody Film film) {
        validateFilm(film);
        if(mapOfFilms.containsKey(film.getId())) {
            log.debug("Поступил post запрос /film на создание объекта Film: Создание невозможно - фильм с указанным id " + film.getId() + " уже создан");
            throw new CheckContainsException("Создание невозможно - фильм с указанным id " + film.getId() + " уже создан");
        }
        log.debug("Поступил post запрос /film на создание объекта Film. Объект успешно создан");
        mapOfFilms.put(film.getId(), film);
    return film;
    }

    //обновление фильма
    @PutMapping("/films")
    public Film updateFilm(@Valid @RequestBody Film film) {
        validateFilm(film);
        if(!mapOfFilms.containsKey(film.getId())) {
            log.debug("Поступил put запрос /film на обновление объекта Film: обновление невозможно - фильм с указанным id " + film.getId() + " отсутствует");
            throw new CheckContainsException("Обновление невозможно - фильм с указанным id " + film.getId() + " отсутствует");
        }
        log.debug("Поступил put запрос /film на обновление объекта Film. Объект успешно обновлен");
        mapOfFilms.put(film.getId(), film);
        return film;
    }

    public void validateFilm(Film film) {
        if(film.getDescription().length() >= 199) {
            log.debug("превышение максимальная длина поля description — 200 символов");
            throw new ValidationException("максимальная длина описания — 200 символов");
        }
        String [] split = film.getReleaseDate().split("\\.");
        System.out.println(film.getReleaseDate());
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        if(year < 1895) {
            log.debug("дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
        if(year == 1985 && month < 12) {
            log.debug("дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
        if(year == 1985 && month == 12 && day <= 28) {
            log.debug("дата релиза — не раньше 28 декабря 1895 года");
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года");
        }
    }

}
