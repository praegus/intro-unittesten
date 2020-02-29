package vakantieplanner.business;

import vakantieplanner.dto.User;

public interface LoginLogic {
    boolean login(User user, String password);
}
