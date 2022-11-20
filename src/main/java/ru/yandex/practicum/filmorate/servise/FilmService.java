package ru.yandex.practicum.filmorate.servise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.customException.FilmIdException;
import ru.yandex.practicum.filmorate.customException.UserIdException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    @Getter
    private final InMemoryFilmStorage inMemoryFilmStorage;

    //добавление и удаление лайка.Пусть пока каждый пользователь может поставить лайк фильму только один раз.
    public void addLike(Integer filmId, Integer userId) {
        inMemoryFilmStorage.getFilmHashMap().get(filmId).getLikes().add(userId);
    }

    public void removeLike(Integer filmId, Integer userId) {
        if(userId < 0) {
            throw new UserIdException("id пользователя не может быть меньше 0");
        }
        inMemoryFilmStorage.getFilmHashMap().get(filmId).getLikes().remove(userId);
    }
    //вывод наиболее популярных фильмов по количеству лайков
    public Collection<Film> topFilms(int count) {
        List<Film> list1 = new ArrayList<>();
        for(Film film: inMemoryFilmStorage.getFilmHashMap().values()) {
            list1.add(film);
        }
        Collections.sort(list1, new FilmLikesComparator());
        int k = count;
        if(count > list1.size()) {
            k = list1.size();
        }
        List<Film> list2 = new ArrayList<>();
        for(int i = 0; i < k; i ++) {
            list2.add(list1.get(i));
        }
        Collections.sort(list2, new FilmLikesComparator());
        return list2;
    }

    public Film getFilm(Integer id) {
        if(inMemoryFilmStorage.getFilmHashMap().containsKey(id)) {
            return inMemoryFilmStorage.getFilmHashMap().get(id);
        } else throw new FilmIdException("фильм под укащанным id отсутствует");
    }
}
