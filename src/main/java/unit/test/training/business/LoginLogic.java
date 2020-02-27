package unit.test.training.business;

import unit.test.training.dto.User;

public interface LoginLogic {
    boolean login(User user, String password);
}
