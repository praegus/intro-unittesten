package vakantieplanner.controller;

import org.junit.jupiter.api.Test;

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
