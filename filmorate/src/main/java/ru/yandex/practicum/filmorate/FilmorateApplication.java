package ru.yandex.practicum.filmorate;

import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class FilmorateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmorateApplication.class, args);
	}

}
