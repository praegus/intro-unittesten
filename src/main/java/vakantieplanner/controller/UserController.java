package vakantieplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vakantieplanner.business.ReserveLogic;
import vakantieplanner.business.UserLogic;
import vakantieplanner.dto.ReserveRequest;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserLogic userLogic;
    private ReserveLogic reserveLogic;

    @Autowired
    public UserController(UserLogic userLogic, ReserveLogic reserveLogic) {
        this.userLogic = userLogic;
        this.reserveLogic = reserveLogic;
    }

    @GetMapping("/all")
    public List<User> retrieveAllUsers() {
        return userLogic.retrieveAllUsersWithCurrentReservation();
    }

    @GetMapping("/names")
    public List<String> listAllUsersByFirstName() {
        return userLogic.retrieveAllUsersWithCurrentReservation().stream().map(User::getFirstname).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public User details(@PathVariable("id") int id) {
        return userLogic.getUserWithCurrentReservation(id);
    }

    @PostMapping(path = "/reserve/{id}")
    public ReserveResponse reserveVacationDates(@PathVariable("id") int id, @RequestBody ReserveRequest reservation) {
        System.out.println("Servering voor gebruiker met id [" + id + "]: " + reservation);

        try {
            // Voorbeeld van input: "2019-03-06 tot 2019-03-22"
            //
            LocalDate startDate = LocalDate.parse(reservation.getDateRange().substring(0, 10));
            LocalDate endDate = LocalDate.parse(reservation.getDateRange().substring(15, 25));
            final User user = userLogic.getUserWithCurrentReservation(id);
            return reserveLogic.makeReservation(user, startDate, endDate);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Vakantie in het verleden", e);
        }
    }
}
