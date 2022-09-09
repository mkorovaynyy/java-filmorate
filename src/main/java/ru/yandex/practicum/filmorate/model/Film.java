package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


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

}
