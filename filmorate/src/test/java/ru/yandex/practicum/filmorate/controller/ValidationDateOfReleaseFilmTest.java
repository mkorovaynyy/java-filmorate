package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.customExseption.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationDateOfReleaseFilmTest {

    @Test
    public void shouldThrowValidationException() {
        FilmController controller = new FilmController();
        Film film1 = new Film(1L, "тестовый1", "дескрипшин тестового1", "1500.10.20", 100L);
        final ValidationException exp = assertThrows(
                ValidationException.class,
                () -> controller.validateFilm(film1)
        );
        Assertions.assertEquals("дата релиза — не раньше 28 декабря 1895 года", exp.getMessage());
    }
}
