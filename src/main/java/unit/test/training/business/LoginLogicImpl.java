package unit.test.training.business;

import org.springframework.stereotype.Component;
import unit.test.training.dto.User;

@Component
public class LoginLogicImpl implements LoginLogic {
    @Override
    public boolean login(User user, String password) {
        return true;
    }
}
