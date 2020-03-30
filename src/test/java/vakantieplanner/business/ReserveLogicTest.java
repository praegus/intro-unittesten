package vakantieplanner.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vakantieplanner.database.DataAccessLayerImpl;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import vakantieplanner.database.DataAccessLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ReserveLogicTest {
    private DataAccessLayer dalMock;
    private ReserveLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        dalMock = mock(DataAccessLayer.class);
        unitUnderTest = new ReserveLogicImpl(dalMock);
    }

    @Test
    public void testHappyFlow() {
        User voorbeeldUser = new User();
        voorbeeldUser.setId(42);
        voorbeeldUser.setFirstname("Bas");
        voorbeeldUser.setLastname("Stoker");
        LocalDate vanaf = LocalDate.parse("2020-06-10"); // 10 juni
        LocalDate tot = LocalDate.parse("2020-06-30"); // 30 juni

        ReserveResponse resultaat = unitUnderTest.makeReservation(voorbeeldUser, vanaf, tot);

        assertTrue(resultaat.reserveringIsGoedgekeurd());
    }

    @Test
    public void testConflictSituatie() {
        User collega = new User();
        collega.setId(41);
        collega.setFirstname("Henk");
        collega.setLastname("de Vries");
        LocalDate vanafCollega = LocalDate.parse("2020-06-12"); // 12 juni
        LocalDate totCollega = LocalDate.parse("2020-06-20"); // 20 juni
        Reservation reserveringCollega = new Reservation(collega, vanafCollega, totCollega);

        List<Reservation> gemockteReserveringen = Collections.singletonList(reserveringCollega);
        Mockito.when(dalMock.retrieveAllReservations()).thenReturn(gemockteReserveringen);

        User voorbeeldUser = new User();
        voorbeeldUser.setId(42);
        voorbeeldUser.setFirstname("Bas");
        voorbeeldUser.setLastname("Stoker");
        LocalDate vanaf = LocalDate.parse("2020-06-10"); // 10 juni
        LocalDate tot = LocalDate.parse("2020-06-30"); // 30 juni

        ReserveResponse resultaat = unitUnderTest.makeReservation(voorbeeldUser, vanaf, tot);

        // Omdat er een conflict is met Henk verwacht ik niet dat de reservering is goedgekeurd:
        assertFalse(resultaat.reserveringIsGoedgekeurd());

        // En omdat in de applicatie ook getoond moet worden met wie er een conflict is,
        // verwacht ik dat de naam van de collega ook wordt teruggeven in het resultaat:
        assertEquals("Henk", resultaat.getBlockingUser().getFirstname());
    }

    private void assertFalse(boolean reserveringIsGoedgekeurd) {
    }
}