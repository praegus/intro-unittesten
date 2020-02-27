package unit.test.training.database;

import unit.test.training.dto.User;

import java.util.List;

public interface DataAccessLayer {
    List<User> getUsers();
}
