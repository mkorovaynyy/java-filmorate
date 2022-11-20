package ru.yandex.practicum.filmorate.servise;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Comparator;

public class FilmLikesComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        if((o1.getLikes().size() - o2.getLikes().size()) < 0) {
            return 1;
        } else if((o1.getLikes().size() - o2.getLikes().size()) > 0) {
            return -1;
        } else if ((o1.getId() - o2.getId()) < 0 ) {
            return 1;
        } else if ((o1.getId() - o2.getId()) > 0 ) {
            return -1;
        } else return 0;
    }
}
