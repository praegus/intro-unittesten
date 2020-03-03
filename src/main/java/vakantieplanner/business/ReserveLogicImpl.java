package vakantieplanner.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ReserveLogicImpl implements ReserveLogic {

    private DataAccessLayer dal;

    @Autowired
    public ReserveLogicImpl(DataAccessLayer dal) {
        this.dal = dal;
    }

    @Override
    public ReserveResponse makeReservation(User user, LocalDate startDate, LocalDate endDate) {
        Reservation newRequest = new Reservation(user, startDate, endDate);
        List<Reservation> existingReservations = dal.retrieveAllReservations();
        final Optional<Reservation> possibleConflictingReservation =
                existingReservations.stream().filter(r -> hasConflict(newRequest, r)).findFirst();
        if (possibleConflictingReservation.isPresent()) {
            Reservation conflict = possibleConflictingReservation.get();
            ReserveResponse response = new ReserveResponse();
            response.setResult(false); // false geeft aan dat de reservering niet is goedgekeurd
            response.setBlockingUser(conflict.user);
            return response;
        } else {
            dal.createReservation(newRequest);
            ReserveResponse response = new ReserveResponse();
            response.setResult(true); // true geeft aan dat de reservering goedgekeurd is
            return response;
        }
    }

    // (EndA <= StartB or StartA >= EndB)
    private boolean hasConflict(Reservation r, Reservation r2) {
        return !((r.endDate.compareTo(r2.startDate) < 0)
            || (r.startDate.compareTo(r2.endDate) >= 0));
    }
}
