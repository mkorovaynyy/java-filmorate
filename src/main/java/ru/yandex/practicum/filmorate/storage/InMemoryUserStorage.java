package ru.yandex.practicum.filmorate.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.customException.CustomValidateException;
import ru.yandex.practicum.filmorate.model.User;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage{

    @Getter
    private HashMap<Integer, User> mapOfUser = new HashMap<>();
    @Getter
    private static Integer idController = 1;

    @Override
    public User create(User user) {
        validate(user);
        user.setId(idController);
        if(mapOfUser.containsValue(user)) {
            log.trace("Данный пользователь уже добавлен в систему");
            throw new CustomValidateException("Данный пользователь уже добавлен в систему");
        }
        mapOfUser.put(user.getId(), user);
        generateId();
        return user;
    }

    @Override
    public User update(User user) {
        validate(user);
        if(!mapOfUser.containsKey(user.getId())) {
            log.trace("Обновление невозможно - пользователь с указанным id " + user.getId() + " отсутствует в системе");
            throw new CustomValidateException("Обновление невозможно - пользователь с указанным id " + user.getId() + " отсутствует в системе");
        }
        mapOfUser.put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return mapOfUser.values();
    }

    @Override
    public void validate(User user) {
        if(user.getLogin().isBlank()) {
            log.trace("логин не может быть пустым и содержать пробелы");
            throw new CustomValidateException("логин не может быть пустым и содержать пробелы");
        }
        if(user.getLogin().contains(" ")) {
            log.trace("логин не может быть пустым и содержать пробелы");
            throw new CustomValidateException("логин не может быть пустым и содержать пробелы");
        }
        String dateToString = user.getBirthday();
        String [] split = dateToString.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        if(date.isAfter(LocalDate.now())) {
            log.trace("дата рождения не может быть в будущем");
            throw new CustomValidateException("дата рождения не может быть в будущем");
        }
        if(user.getName().isBlank()) {
            log.trace("вместо имени пользователя будет использоваться логин");
            user.setName(user.getLogin());
        }
        if(user.getName() == "null") {
            log.trace("вместо имени пользователя будет использоваться логин");
            user.setName(user.getLogin());
        }
    }
    @Override
    public Integer generateId() {
        idController++;
        return idController;
    }
}
