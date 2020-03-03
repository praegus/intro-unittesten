package vakantieplanner.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogicImpl implements UserLogic {

    private DataAccessLayer dal;

    @Autowired
    public UserLogicImpl(DataAccessLayer dal) {
        this.dal = dal;
    }

    @Override
    public List<User> retrieveAllUsersWithCurrentReservation() {
        return dal.retrieveAllUsers().stream().map(u -> {
            u.setReservation(dal.getReservationsForUser((u.getId())).orElse(null));
            return u;
        }).collect(Collectors.toList());
    }

    @Override
    public User getUserWithCurrentReservation(Integer id) {
        return retrieveAllUsersWithCurrentReservation().stream().filter(u -> u.getId() == id).findFirst().get();
    }
}
