package ru.yandex.practicum.filmorate.customException;

public class CustomValidateException extends RuntimeException {
    public CustomValidateException() {
    }

    public CustomValidateException(String message) {
        super(message);
    }

    public CustomValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomValidateException(Throwable cause) {
        super(cause);
    }

}
