package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.customException.CustomValidateException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.servise.FilmService;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilmControllerTest {

    @Test
    public void shouldThrowException() {
        InMemoryFilmStorage filmStorage = new InMemoryFilmStorage();
        FilmService service = new FilmService(filmStorage);
        FilmController controller = new FilmController(service);
        Film film1 = new Film("film1", "ds f1", "1500-6-5", 120L);
        final CustomValidateException exp1 = assertThrows(
                CustomValidateException.class,
                () -> controller.getFilmService().getInMemoryFilmStorage().validate(film1)
        );
        Assertions.assertEquals("дата релиза — не раньше 28 декабря 1985", exp1.getMessage());
        Film film2 = new Film("film2", "Пятеро друзей ( комик-группа «Шарло»), приезжают в город Бризуль." +
                " Здесь они хотят разыскать господина Огюста Куглова, который задолжал им деньги, а именно 20 миллионов." +
                " о Куглов, который за время «своего отсутствия», стал кандидатом Коломбани.", "2000-6-5", 120L);
        final CustomValidateException exp2 = assertThrows(
                CustomValidateException.class,
                () -> controller.getFilmService().getInMemoryFilmStorage().validate(film2)
        );
        Assertions.assertEquals("максимальная длина описания — 200 символов", exp2.getMessage());

    }
}
