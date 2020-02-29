package vakantieplanner.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserLogicImpl implements UserLogic {

    @Autowired
    DataAccessLayer dal;

    @Autowired
    ReserveLogic reserveLogic;


    @Override
    public List<User> getUsers() {
        return dal.getUsers().stream().map(u -> {
            u.setReservation(reserveLogic.getExistingReservationForUser(u.getId()));
            return u;
            }).collect(Collectors.toList());
    }

    @Override
    public User getUser(int id) {
        return getUsers().stream().filter(u -> u.getId() == id).findFirst().get();
    }
}
