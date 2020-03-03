package vakantieplanner.business;

import vakantieplanner.dto.User;

import java.util.List;

public interface UserLogic {
    public List<User> retrieveAllUsersWithCurrentReservation();
    public User getUserWithCurrentReservation(Integer id);
}
