package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Data
public class Film implements Comparable<Film>{
    private Integer id;
    @NotBlank
    private final String name;
    private final String description;
    private final String releaseDate;
    @Positive
    private final Long duration;
    @Positive
    private Integer rate;
    private HashMap<Integer, User> likes = new HashMap<>();

    @Override
    public int compareTo(Film o) {
        return this.getLikes().size() - o.getLikes().size();
    }
}
