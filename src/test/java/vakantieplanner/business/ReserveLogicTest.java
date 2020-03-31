package vakantieplanner.business;

import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveLogicTest {
    private DataAccessLayer dalMock;
    private TodayService todayMock;
    private ReserveLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        dalMock = mock(DataAccessLayer.class);
        todayMock = mock(TodayService.class);
        unitUnderTest = new ReserveLogicImpl(dalMock, todayMock);

        // Vandaag is in deze unittest altijd 10 juni 2020:
        when(todayMock.getToday()).thenReturn(LocalDate.parse("2020-06-10"));
    }

    // Regressietestgeval Bug 1
    @Test
    public void conflictMetEigenVakantie() {
        User ikzelf = new User();
        ikzelf.setId(41);
        ikzelf.setFirstname("Henk");
        ikzelf.setLastname("de Vries");
        LocalDate vanaf = LocalDate.parse("2020-06-12"); // 12 juni
        LocalDate tot = LocalDate.parse("2020-06-20"); // 20 juni
        Reservation reservering = new Reservation(ikzelf, vanaf, tot);

        List<Reservation> gemockteReserveringen = Collections.singletonList(reservering);
        when(dalMock.retrieveAllReservations()).thenReturn(gemockteReserveringen);

        ReserveResponse resultaat = unitUnderTest.makeReservation(ikzelf, vanaf, tot);

        // Omdat dit een reservering is van mijzelf verwacht ik geen problemen met de goedkeuring:
        assertTrue(resultaat.reserveringIsGoedgekeurd());
    }

    // Regressietestgeval Bug 2, scenario 1, een dag later
    @Test
    public void conflictMetVakantieEenDagLater() {
        User collega = new User();
        collega.setId(61);
        collega.setFirstname("Piet");
        collega.setLastname("Janssen");
        LocalDate vanafCollega = LocalDate.parse("2020-06-12"); // 12 juni
        LocalDate totCollega = LocalDate.parse("2020-06-20"); // 20 juni
        Reservation reserveringCollega = new Reservation(collega, vanafCollega, totCollega);

        List<Reservation> gemockteReserveringen = Collections.singletonList(reserveringCollega);
        when(dalMock.retrieveAllReservations()).thenReturn(gemockteReserveringen);

        User ikzelf = new User();
        ikzelf.setId(62);
        ikzelf.setFirstname("Henk");
        ikzelf.setLastname("de Vries");
        LocalDate vanaf = LocalDate.parse("2020-06-21"); // 21 juni, een dag nadat de vakantie van Piet is afgelopen
        LocalDate tot = LocalDate.parse("2020-06-30"); // 30 juni
        Reservation reservering = new Reservation(ikzelf, vanaf, tot);

        ReserveResponse resultaat = unitUnderTest.makeReservation(ikzelf, vanaf, tot);

        // Omdat de beide reserveringen niet overlappen(alleen aansluiten qua dagen)
        // verwacht ik geen problemen met de goedkeuring:
        assertTrue(resultaat.reserveringIsGoedgekeurd());
    }

    // Regressietestgeval Bug 2, scenario 2, een dag eerder
    @Test
    public void conflictMetVakantieEenDagEerder() {
        User collega = new User();
        collega.setId(61);
        collega.setFirstname("Piet");
        collega.setLastname("Janssen");
        LocalDate vanafCollega = LocalDate.parse("2020-07-12"); // 12 juli
        LocalDate totCollega = LocalDate.parse("2020-07-20"); // 20 juli
        Reservation reserveringCollega = new Reservation(collega, vanafCollega, totCollega);

        List<Reservation> gemockteReserveringen = Collections.singletonList(reserveringCollega);
        when(dalMock.retrieveAllReservations()).thenReturn(gemockteReserveringen);

        User ikzelf = new User();
        ikzelf.setId(62);
        ikzelf.setFirstname("Henk");
        ikzelf.setLastname("de Vries");
        LocalDate vanaf = LocalDate.parse("2020-06-14"); // 14 juni
        LocalDate tot = LocalDate.parse("2020-07-11"); // 11 juli, een dag voordat de vakantie van Piet begint
        Reservation reservering = new Reservation(ikzelf, vanaf, tot);

        ReserveResponse resultaat = unitUnderTest.makeReservation(ikzelf, vanaf, tot);

        // Omdat de beide reserveringen niet overlappen(alleen aansluiten qua dagen)
        // verwacht ik geen problemen met de goedkeuring:
        assertTrue(resultaat.reserveringIsGoedgekeurd());
    }

    // Regressietestgeval Bug 3, vakantie mag niet in het verleden liggen
    @Test
    public void vakantieInplannenInHetVerledenGeeftFout() {
        User ikzelf = new User();
        ikzelf.setId(62);
        ikzelf.setFirstname("Henk");
        ikzelf.setLastname("de Vries");
        LocalDate vanaf = LocalDate.parse("2020-06-04");
        LocalDate tot = LocalDate.parse("2020-06-09"); // vakantie eindigt gisteren
        Reservation reservering = new Reservation(ikzelf, vanaf, tot);

        // Omdat de reservering in het verleden ligt verwacht ik een Exception:
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ReserveResponse resultaat = unitUnderTest.makeReservation(ikzelf, vanaf, tot);
        });
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
        when(dalMock.retrieveAllReservations()).thenReturn(gemockteReserveringen);

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
}