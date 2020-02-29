package vakantieplanner.database;

import vakantieplanner.dto.User;

import java.util.List;

public interface DataAccessLayer {
    List<User> getUsers();
}
