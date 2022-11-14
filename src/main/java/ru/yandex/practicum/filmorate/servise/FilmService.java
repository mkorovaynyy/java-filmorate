package ru.yandex.practicum.filmorate.servise;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class FilmService {

    //добавление и удаление лайка.Пусть пока каждый пользователь может поставить лайк фильму только один раз.
    public void addLike(InMemoryFilmStorage inMemoryFilmStorage, InMemoryUserStorage inMemoryUserStorage, Integer filmId, Integer userId) {
        inMemoryFilmStorage.getFilmHashMap().get(filmId).getLikes().add(userId);
    }

    public void removeLike(InMemoryFilmStorage inMemoryFilmStorage, Integer filmId, Integer userId) {
        inMemoryFilmStorage.getFilmHashMap().get(filmId).getLikes().remove(userId);
    }
    //вывод 10 наиболее популярных фильмов по количеству лайков
    public Collection<Film> topFilms(InMemoryFilmStorage inMemoryFilmStorage, int count) {
        ArrayList<Film> fullListOfFilms = new ArrayList<>();
        for(Film film: inMemoryFilmStorage.getFilmHashMap().values()) {
            fullListOfFilms.add(film);
        }
        Collections.sort(fullListOfFilms);
        ArrayList<Film> topListOfFilms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            topListOfFilms.add(fullListOfFilms.get(i));
        }
        return topListOfFilms;
    }

}
