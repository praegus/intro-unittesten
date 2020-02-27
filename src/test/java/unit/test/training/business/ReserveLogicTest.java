package unit.test.training.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.test.training.dto.ReserveResponse;
import unit.test.training.dto.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReserveLogicTest {
    private ReserveLogic unitUnderTest;

    @BeforeEach
    public void init() {
        unitUnderTest = new ReserveLogicImpl();
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
