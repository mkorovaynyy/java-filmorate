package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storageInterfase.MpaStorage;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaService {
    private final MpaStorage mpaDbStorage;

    public Mpa getById(int id) {
        log.info("Рейтинг отправлен");

        return mpaDbStorage.getById(id).orElseThrow(() -> {
            log.warn("Рейтинг {} не найден.", id);
            throw new ObjectNotFoundException("Рейтинг не найден");
        });
    }

    public Collection<Mpa> findAll() {
        log.info("Список рейтингов отправлен");

        return mpaDbStorage.findAll();
    }


}