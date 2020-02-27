package unit.test.training.business;

import unit.test.training.dto.User;

import java.util.List;

public interface UserLogic {
    List<User> getUsers();

    User getUser(int id);
}
