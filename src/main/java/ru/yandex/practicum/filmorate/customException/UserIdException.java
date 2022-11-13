package ru.yandex.practicum.filmorate.customException;

public class UserIdException extends IllegalArgumentException{
    public UserIdException() {
    }

    public UserIdException(String message) {
        super(message);
    }

    public UserIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdException(Throwable cause) {
        super(cause);
    }
}
