package ru.yandex.practicum.filmorate.servise;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.customException.UserIdException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {

    //добавление в друзья
    public void addFriend(InMemoryUserStorage inMemoryUserStorage, Integer idUser1, Integer idUser2) {
        if(idUser1 < 0 || idUser2 < 0) {
            throw new UserIdException("id должен быть больше 0");
        }
        inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends().add(idUser2);
        inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends().add(idUser1);
    }

    //удаления из друзей
    public void removeFriend(InMemoryUserStorage inMemoryUserStorage, Integer idUser1, Integer idUser2) {
        inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends().remove(idUser2);
        inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends().remove(idUser1);
    }
    //вывод списка  друзей
    public Collection<Integer> retListOfFriends(InMemoryUserStorage inMemoryUserStorage, Integer idUser) {
        return inMemoryUserStorage.getMapOfUser().get(idUser).getFriends();
    }

    //вывод списка общих друзей
    public Collection<Integer> retListOfCommonFriends(InMemoryUserStorage inMemoryUserStorage, Integer idUser1, Integer idUser2) {
        ArrayList<Integer> listOfFriends = new ArrayList<>();
        for(Integer user: inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends()) {
            if (inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends().contains(user)) {
                listOfFriends.add(user);
            }
        }
        return listOfFriends;
    }
}
