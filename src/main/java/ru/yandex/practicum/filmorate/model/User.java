package ru.yandex.practicum.filmorate.model;

import lombok.Data;


import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Data
public class User {
    private Integer id;
    @Email
    private final String email;
    private final String login;
    private String name = "";
    private final String birthday;
    private Set<Integer> friends = new HashSet<>();
}
