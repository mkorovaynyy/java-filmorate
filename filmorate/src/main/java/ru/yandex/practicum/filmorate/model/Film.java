package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Slf4j
@Data
public class Film {
    @NotNull
    private final Long id;
    @NotBlank
    private final String name;
    @Length(max = 200)
    private final String description;
    @NotNull
    private final String releaseDate;
    @Positive
    private final Long duration;
}
