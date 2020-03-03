package vakantieplanner.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vakantieplanner.database.DataAccessLayer;
import vakantieplanner.dto.ReserveResponse;
import vakantieplanner.dto.User;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class ReserveLogicTest {
    private ReserveLogic unitUnderTest;

    @BeforeEach
    public void init() {
        DataAccessLayer database = mock(DataAccessLayer.class);
        unitUnderTest = new ReserveLogicImpl(database);
    }

    @Test
    public void test() {
        User userMock = mock(User.class);
        LocalDate vanaf = LocalDate.parse("2019-03-29");
        LocalDate tot = LocalDate.parse("2019-04-12");

        ReserveResponse resultaat = unitUnderTest.makeReservation(userMock, vanaf, tot);

        // hier asserts toevoegen:
    }

}
