package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.customException.CustomValidateException;
import ru.yandex.practicum.filmorate.model.Film;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage{

    @Getter
    private HashMap<Integer, Film> filmHashMap = new HashMap<>();
    @Getter
    private static Integer idController = 1;


    @Override
    public Film create(Film film) {
        validate(film);
        film.setId(idController);
        if(filmHashMap.containsValue(film)) {
            log.trace("Данный Фильм уже содержится в рейтинге");
            throw new CustomValidateException("Данный Фильм уже содержится в рейтинге");
        }
        filmHashMap.put(film.getId(), film);
        generateId();
        return film;
    }

    @Override
    public Film update(Film film) {
        validate(film);
        if(filmHashMap.containsKey(film.getId())) {
            filmHashMap.put(film.getId(), film);
        } else {
            log.trace("Обновление невозможно - фильм с указанным id " + film.getId() + " отсутствует в рейтинге");
            throw new CustomValidateException("Обновление невозможно - фильм с указанным id " + film.getId() + " отсутствует в рейтинге");
        }
        return film;
    }

    @Override
    public Collection<Film> getAll() {
        return filmHashMap.values();
    }

    @Override
    public void validate(Film film) {
        if (film.getDescription().length() > 200) {
            log.trace("максимальная длина описания — 200 символов");
            throw new CustomValidateException("максимальная длина описания — 200 символов");
        }
        String dateToString = film.getReleaseDate();
        String [] split = dateToString.split("-");
        Date date = new Date(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        if(date.before(new Date(1895, 12, 25))) {
            log.trace("дата релиза — не раньше 28 декабря 1985");
            throw new CustomValidateException("дата релиза — не раньше 28 декабря 1985");
        }
    }
    @Override
    public Integer generateId() {
        idController =  filmHashMap.size() + 1;
        return idController;
    }
}
