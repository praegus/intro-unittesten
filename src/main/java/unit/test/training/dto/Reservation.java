package unit.test.training.dto;

import java.time.LocalDate;

public class Reservation {
    public User user;
    public LocalDate startDate, endDate;

    public Reservation(User user, LocalDate startDate, LocalDate endDate) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
