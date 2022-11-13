package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.customException.FilmIdException;
import ru.yandex.practicum.filmorate.customException.UserIdException;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHappinessOverflow(final NullPointerException e) {
        return Map.of("исключение:", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHappinessOverflow(final NumberFormatException e) {
        return Map.of("исключение:", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleHappinessOverflow(final IllegalArgumentException e) {
        return Map.of("исключение:", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleHappinessOverflow(final UserIdException e) {
        return Map.of("исключение:", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleHappinessOverflow(final FilmIdException e) {
        return Map.of("исключение:", e.getMessage());
    }
}
