package unit.test.training.business;

import unit.test.training.dto.Reservation;
import unit.test.training.dto.ReserveRequest;
import unit.test.training.dto.ReserveResponse;
import unit.test.training.dto.User;

import java.time.LocalDate;

public interface ReserveLogic {
    ReserveResponse makeReservation(User user, LocalDate startDate, LocalDate endDate);
    Reservation getExistingReservationForUser(int id);
}
