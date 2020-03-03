package vakantieplanner.database;

import org.springframework.stereotype.Component;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.util.*;

@Component
public class DataAccessLayerImpl implements DataAccessLayer {

    private Map<Integer, Reservation> reservationsByUserId = new HashMap<>();

    @Override
    public List<User> retrieveAllUsers() {
        return Arrays.asList(
                User.UserBuilder.withValues(1, "Paul", "MacDonald", "paul.macdonald@acme.corp"),
                User.UserBuilder.withValues(2, "Anna", "Welch", "anna.welch@acme.corp"),
                User.UserBuilder.withValues(3, "Luke", "Hart", "luke.hart@acme.corp"),
                User.UserBuilder.withValues(4, "Kimberly", "Morgan", "kimberly.morgan@acme.corp"),
                User.UserBuilder.withValues(5, "Dan", "MacLeod", "dan.macleod@acme.corp"),
                User.UserBuilder.withValues(6, "Anthony", "Howard", "anthony.howard@acme.corp"),
                User.UserBuilder.withValues(7, "Jake", "Bailey", "jake.bailey@acme.corp"),
                User.UserBuilder.withValues(8, "Joanne", "Welch", "joanne.welch@acme.corp")
        );
    }

    @Override
    public List<Reservation> retrieveAllReservations() {
        return new ArrayList(reservationsByUserId.values());
    }

    @Override
    public Optional<Reservation> getReservationsForUser(Integer userId) {
        return Optional.ofNullable(reservationsByUserId.get(userId));
    }

    @Override
    public void createReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation is null");
        }
        if (reservation.user == null || reservation.user.getId() == null) {
            throw new IllegalArgumentException("User with ID is mandatory");
        }
        Integer userId = reservation.user.getId();
        if (reservationsByUserId.containsKey(userId)) {
            throw new IllegalStateException("User has an existing reservation");
        }
        reservationsByUserId.put(reservation.user.getId(), reservation);
    }
}