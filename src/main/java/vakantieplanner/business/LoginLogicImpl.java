package vakantieplanner.business;

import org.springframework.stereotype.Component;
import vakantieplanner.dto.User;

@Component
public class LoginLogicImpl implements LoginLogic {
    @Override
    public boolean login(User user, String password) {
        return true;
    }
}
