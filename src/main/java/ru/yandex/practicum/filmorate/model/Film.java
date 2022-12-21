package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @PositiveOrZero
    private int id;
    @NotBlank(message = "Название фильма не может быть пустым")
    @Size(max = 60, message = "Название фильма не может быть больше 60 символов")
    private String name;
    @NotNull(message = "Отсутствует описание фильма")
    @Size(min = 1, max = 200, message = "Описание превышает 200 символов")
    private String description;
    private LocalDate releaseDate;
    @Min(value = 1, message = "Некорректная продолжительность фильма")
    private long duration;
    private Mpa mpa;
    private LinkedHashSet<Genre> genres;
}