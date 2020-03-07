package vakantieplanner.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vakantieplanner.database.DataAccessLayerImpl;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;

import vakantieplanner.database.DataAccessLayer;
import static org.mockito.Mockito.mock;

public class ReserveLogicTest {
    private ReserveLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        // Mock hier de DatabaseAccessLayer (dal)

        // Vervang de echte 'dal' hieronder door de mock die hierboven gemaakt is:
        unitUnderTest = new ReserveLogicImpl(new DataAccessLayerImpl());
    }

    @Test
    public void test() {
        User voorbeeldUser = new User();
        voorbeeldUser.setId(42);
        voorbeeldUser.setFirstname("Bas");
        voorbeeldUser.setLastname("Stoker");
        LocalDate vanaf = LocalDate.parse("2020-06-10"); // 10 juni
        LocalDate tot = LocalDate.parse("2020-06-30"); // 30 juni

        ReserveResponse resultaat = unitUnderTest.makeReservation(voorbeeldUser, vanaf, tot);

        if (resultaat.reserveringIsGoedgekeurd()) {
            System.out.println("Reservering is goedgekeurd");
        } else {
            System.out.println("Reservering is afgekeurd, er is een conflict met " + resultaat.getBlockingUser());
        }

        // hier asserts toevoegen:
    }
}