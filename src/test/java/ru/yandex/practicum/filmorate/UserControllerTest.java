package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.customException.CustomValidateException;
import ru.yandex.practicum.filmorate.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest {

    @Test
    public void shouldThrowException() {
        UserController controller = new UserController();
        User user1 = new User("sobaka1@mail.com", "log in", "2000-12-25");
        final CustomValidateException exp1 = assertThrows(
                CustomValidateException.class,
                () -> controller.validateUser(user1)
        );
        Assertions.assertEquals("логин не может быть пустым и содержать пробелы", exp1.getMessage());

        User user2 = new User("sobaka2@mail.com", " ", "2000-11-25");
        final CustomValidateException exp2 = assertThrows(
                CustomValidateException.class,
                () -> controller.validateUser(user2)
        );
        Assertions.assertEquals("логин не может быть пустым и содержать пробелы", exp2.getMessage());

        User user3 = new User("sobaka3@mail.com", "login3", "3000-12-25");
        final CustomValidateException exp3 = assertThrows(
                CustomValidateException.class,
                () -> controller.validateUser(user3)
        );
        Assertions.assertEquals("дата рождения не может быть в будущем", exp3.getMessage());
    }
}
