package vakantieplanner.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLogicMockitoTest {
    private DataAccessLayer dalMock;

    private UserLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        // Maak hier de DataAccessLayer en UserLogic mocks aan m.b.v. Mockito
        // (maak hierbij dus geen gebruik van de POJO-mock DataAccessLayerMock.java)

    }

    // Let op: Haal @Disabled hier weg als setup af is:
    @Disabled
    @Test
    public void testOphalenGebruiker() {
        User resultaat = unitUnderTest.getUserWithCurrentReservation(3);
        System.out.println("Opgehaalde gebruiker uit onze mock: " + resultaat);

        assertEquals(3, resultaat.getId().intValue());
        assertEquals("Henk", resultaat.getFirstname());
        assertEquals("Jansen", resultaat.getLastname());
    }

    // Let op: Haal @Disabled hier weg als setup af is:
    @Disabled
    @Test
    public void testOphalenGebruikerMetBestaandeReservering() {
        User resultaat = unitUnderTest.getUserWithCurrentReservation(3);
        Reservation reservering = resultaat.getReservation();

        assertEquals(LocalDate.parse("2020-06-10"), reservering.getStartDate());
        assertEquals(LocalDate.parse("2020-06-20"), reservering.getEndDate());
        assertEquals("Henk", reservering.getUser().getFirstname());
    }
}
