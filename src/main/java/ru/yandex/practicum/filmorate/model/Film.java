package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Data
public class Film {
    private Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final String releaseDate;
    @Positive
    private final Long duration;
    @Positive
    private Integer rate;
    private Set<Integer> likes = new HashSet<>();

}
