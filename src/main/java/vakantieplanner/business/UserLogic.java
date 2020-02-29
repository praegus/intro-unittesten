package vakantieplanner.business;

import vakantieplanner.dto.User;

import java.util.List;

public interface UserLogic {
    List<User> getUsers();

    User getUser(int id);
}
