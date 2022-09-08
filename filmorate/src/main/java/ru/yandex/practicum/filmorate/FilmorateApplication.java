package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.Duration;

@SpringBootApplication
public class FilmorateApplication {

	public static void main(String[] args) {
		Film film1 = new Film("test1", "test1 ds", "2000.08.12", 120L);
		SpringApplication.run(FilmorateApplication.class, args);
	}

}
