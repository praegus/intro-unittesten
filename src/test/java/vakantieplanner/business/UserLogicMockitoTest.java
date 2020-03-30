package vakantieplanner.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLogicMockitoTest {
    private DataAccessLayer dalMock;

    private UserLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        dalMock = Mockito.mock(DataAccessLayer.class);
        unitUnderTest = new UserLogicImpl(dalMock);

        User voorbeeldUser = new User();
        voorbeeldUser.setId(3);
        voorbeeldUser.setFirstname("Henk");
        voorbeeldUser.setLastname("Jansen");
        List<User> users = Collections.singletonList(voorbeeldUser);

        Mockito.when(dalMock.retrieveAllUsers()).thenReturn(users);

        LocalDate vanaf = LocalDate.parse("2020-06-10"); // 10 juni
        LocalDate tot = LocalDate.parse("2020-06-20"); // 20 juni

        Reservation reservering = new Reservation(voorbeeldUser, vanaf, tot);
        Mockito.when(dalMock.getReservationsForUser(3)).thenReturn(Optional.of(reservering));
    }

    @Test
    public void testOphalenGebruiker() {
        User resultaat = unitUnderTest.getUserWithCurrentReservation(3);
        System.out.println("Opgehaalde gebruiker uit onze mock: " + resultaat);

        assertEquals(3, resultaat.getId().intValue());
        assertEquals("Henk", resultaat.getFirstname());
        assertEquals("Jansen", resultaat.getLastname());
    }

    @Test
    public void testOphalenGebruikerMetBestaandeReservering() {
        User resultaat = unitUnderTest.getUserWithCurrentReservation(3);
        Reservation reservering = resultaat.getReservation();

        assertEquals(LocalDate.parse("2020-06-10"), reservering.getStartDate());
        assertEquals(LocalDate.parse("2020-06-20"), reservering.getEndDate());
        assertEquals("Henk", reservering.getUser().getFirstname());
    }

    @Test
    public void testOphalenGebruikerDieNietBestaat() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            User resultaat = unitUnderTest.getUserWithCurrentReservation(4);
        });
    }
}
