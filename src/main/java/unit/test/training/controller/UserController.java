package unit.test.training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unit.test.training.business.ReserveLogic;
import unit.test.training.business.UserLogic;
import unit.test.training.dto.ReserveRequest;
import unit.test.training.dto.ReserveResponse;
import unit.test.training.dto.User;

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
        return userLogic.getUsers();
    }

    @GetMapping("/names")
    public List<String> listAllUsersByFirstName() {
        return userLogic.getUsers().stream().map(User::getFirstname).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public User details(@PathVariable("id") int id) {
        return userLogic.getUser(id);
    }

    @PostMapping(path = "/reserve/{id}")
    public ReserveResponse reserveVacationDates(@PathVariable("id") int id, @RequestBody ReserveRequest reservation) {
        System.out.println("Servering voor gebruiker met id [" + id + "]: " + reservation);

        // parse startDate en endDate
        // Bijvoorbeeld: "2019-03-06 tot 2019-03-22"
        //
        LocalDate startDate = LocalDate.parse(reservation.getDateRange().substring(0, 10));
        LocalDate endDate = LocalDate.parse(reservation.getDateRange().substring(15, 25));
        final User user = userLogic.getUser(id);
        return reserveLogic.makeReservation(user, startDate, endDate);
    }
}
