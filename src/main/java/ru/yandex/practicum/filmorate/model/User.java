package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class User {
    private Integer id;
    @Email
    private final String email;
    private final String login;
    @NotNull
    private String name;
    private final String birthday;
}
