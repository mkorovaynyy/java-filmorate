package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
@Slf4j
@Data
public class User {
    @NotNull
    private final Long id;
    @Email
    private final String email;
    @NotBlank
    private final String login;
    private String name;
    @Past
    private final Date birthday;
}
