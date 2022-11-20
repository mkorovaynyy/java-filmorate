package ru.yandex.practicum.filmorate.servise;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.customException.UserIdException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    @Getter
    private final InMemoryUserStorage inMemoryUserStorage;

    //добавление в друзья
    public void addFriend(Integer idUser1, Integer idUser2) {
        if(idUser1 < 0 || idUser2 < 0) {
            throw new UserIdException("id должен быть больше 0");
        }
        inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends().add(idUser2);
        inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends().add(idUser1);
    }

    //удаления из друзей
    public void removeFriend(Integer idUser1, Integer idUser2) {
        inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends().remove(idUser2);
        inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends().remove(idUser1);
    }
    //вывод списка  друзей
    public Collection<User> retListOfFriends(Integer idUser) {
        Set<Integer> idUsers = inMemoryUserStorage.getMapOfUser().get(idUser).getFriends();
        ArrayList<User> users = new ArrayList<>();
        for (Integer id: idUsers) {
            users.add(inMemoryUserStorage.getMapOfUser().get(id));
        }
        return users;
    }

    //вывод списка общих друзей
    public Collection<User> retListOfCommonFriends(Integer idUser1, Integer idUser2) {
        if(!(inMemoryUserStorage.getMapOfUser().containsKey(idUser1)) || !(inMemoryUserStorage.getMapOfUser().containsKey(idUser2))) {
            throw new UserIdException("проверьте корректность id пользователей");
        }
        Set<Integer> user1FriendsId = inMemoryUserStorage.getMapOfUser().get(idUser1).getFriends();
        Set<Integer> user2FriendsId = inMemoryUserStorage.getMapOfUser().get(idUser2).getFriends();
        Set<Integer> commonFriendsId = new HashSet<>();
        for(Integer id: user1FriendsId) {
            if(user2FriendsId.contains(id)) {
                commonFriendsId.add(id);
            }
        }
        ArrayList<User> commonFriends = new ArrayList<>();
        for(Integer idCommonFriends: commonFriendsId) {
            commonFriends.add(inMemoryUserStorage.getMapOfUser().get(idCommonFriends));
        }
        return commonFriends;
    }
    public User getUserById(Integer idUser) {
        if(inMemoryUserStorage.getMapOfUser().containsKey(idUser)) {
            return inMemoryUserStorage.getMapOfUser().get(idUser);
        } else throw new UserIdException("пользователя с указанным id не существует");
    }
}
