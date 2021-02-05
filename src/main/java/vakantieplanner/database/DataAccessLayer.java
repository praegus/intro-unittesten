package vakantieplanner.database;

import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DataAccessLayer {
    List<User> retrieveAllUsers();

    List<Reservation> retrieveAllReservations();

    Optional<Reservation> getReservationsForUser(Integer userId);

    void removeReservationForUser(Integer userId);

    void createReservation(Reservation reservation);
}
