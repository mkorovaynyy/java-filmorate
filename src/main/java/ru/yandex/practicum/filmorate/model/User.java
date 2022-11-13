package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.HashMap;


@Data
public class User {
    private Integer id;
    @Email
    private final String email;
    private final String login;
    private String name = "";
    private final String birthday;
    private HashMap<Integer, User> friends = new HashMap<>();
}
