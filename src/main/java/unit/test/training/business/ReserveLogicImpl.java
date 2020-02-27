package unit.test.training.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unit.test.training.dto.Reservation;
import unit.test.training.dto.ReserveRequest;
import unit.test.training.dto.ReserveResponse;
import unit.test.training.dto.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ReserveLogicImpl implements ReserveLogic {

    public ReserveLogicImpl() {
    }

    private Map<Integer, Reservation> reservations = new HashMap<>();

    @Override
    public ReserveResponse makeReservation(User user, LocalDate startDate, LocalDate endDate) {
        Reservation request = new Reservation(user, startDate, endDate);

        final Optional<Map.Entry<Integer, Reservation>> conflictOption =
                reservations.entrySet().stream().filter(e -> hasConflict(request, e.getValue())).findFirst();
        if (conflictOption.isPresent()) {
            Map.Entry<Integer, Reservation> conflict = conflictOption.get();
            ReserveResponse response = new ReserveResponse();
            response.setResult(false);
            response.setBlockingUser(conflict.getValue().user);
            return response;
        } else {
            reservations.put(user.getId(), request);
            ReserveResponse response = new ReserveResponse();
            response.setResult(true);
            return response;
        }
    }

    @Override
    public Reservation getExistingReservationForUser(int id) {
        return reservations.get(id);
    }

    // (EndA <= StartB or StartA >= EndB)
    private boolean hasConflict(Reservation request, Reservation value) {
        return !((request.endDate.compareTo(value.startDate) < 0)
            || (request.startDate.compareTo(value.endDate) >= 0));
    }
}
