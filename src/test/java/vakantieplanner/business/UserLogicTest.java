package vakantieplanner.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.Reservation;
import vakantieplanner.dto.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLogicTest {
    private DataAccessLayer dalMock;

    private UserLogic unitUnderTest;

    @BeforeEach
    public void setup() {
        dalMock = new DataAccessLayerMock();
        unitUnderTest = new UserLogicImpl(dalMock);
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
}
