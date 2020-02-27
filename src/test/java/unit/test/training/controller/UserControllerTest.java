package unit.test.training.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

public class UserControllerTest {
    @Test
    public void testStringLogica() {
        // Voorbeeld hoe om te gaan met datums parsen in Java:

        String voorbeeldRequest = "2019-03-06 tot 2019-03-22";
        //
        LocalDate startDate = LocalDate.parse(voorbeeldRequest.substring(0, 10));
        LocalDate endDate = LocalDate.parse(voorbeeldRequest.substring(15, 25));

        System.out.println(startDate);
        System.out.println(endDate);
    }
}
