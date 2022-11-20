package ru.yandex.practicum.filmorate.customException;

public class FilmIdException extends IllegalArgumentException{
    public FilmIdException() {
    }

    public FilmIdException(String message) {
        super(message);
    }

    public FilmIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilmIdException(Throwable cause) {
        super(cause);
    }
}
