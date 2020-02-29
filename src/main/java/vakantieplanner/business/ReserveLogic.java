package vakantieplanner.business;

import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;

public interface ReserveLogic {
    ReserveResponse makeReservation(User user, LocalDate startDate, LocalDate endDate);
    Reservation getExistingReservationForUser(int id);
}
