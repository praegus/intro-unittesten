package vakantieplanner.business;

import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DataAccessLayerMock implements DataAccessLayer {
    @Override
    public List<User> retrieveAllUsers() {
        User voorbeeldUser = new User();
        voorbeeldUser.setId(3);
        voorbeeldUser.setFirstname("Henk");
        voorbeeldUser.setLastname("Jansen");
        return Collections.singletonList(voorbeeldUser);
    }

    @Override
    public List<Reservation> retrieveAllReservations() {
        return null;
    }

    @Override
    public Optional<Reservation> getReservationsForUser(Integer userId) {
        if (userId.equals(3)) {
            User voorbeeldUser = new User();
            voorbeeldUser.setId(3);
            voorbeeldUser.setFirstname("Henk");
            voorbeeldUser.setLastname("Jansen");

            LocalDate vanaf = LocalDate.parse("2020-06-10"); // 10 juni
            LocalDate tot = LocalDate.parse("2020-06-20"); // 20 juni
            Reservation reservering = new Reservation(voorbeeldUser, vanaf, tot);

            return Optional.of(reservering);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void createReservation(Reservation reservation) {
    }
}
