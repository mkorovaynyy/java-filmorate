package ru.yandex.practicum.filmorate.customExseption;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckContainsException extends RuntimeException {

    public CheckContainsException() {
    }
    public CheckContainsException(String message) {
        super(message);
    }

    public CheckContainsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckContainsException(Throwable cause) {
        super(cause);
    }
}
