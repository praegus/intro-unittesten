package codefromvideo.lifecycle;

import codefromvideo.junit.Palindroom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PalindroomTestMetSetup {

    @ParameterizedTest(name = "Test dat Palindroom voor {0} de waarde {1} teruggeeft")
    @CsvSource({
            "abba,  true",
            "sos,   true",
            "'',    true",
            "abcde, false"
    })
    public void geparameteriseerdeTest(String voorbeeld, boolean isPalindroom) {
        Palindroom palindroom = new Palindroom();
        assertEquals(isPalindroom, palindroom.isPalindroom(voorbeeld));
    }

    @Test
    public void geldigePalindroom() {
        Palindroom palindroom = new Palindroom();
        assertTrue(palindroom.isPalindroom("abba"));
        assertTrue(palindroom.isPalindroom("sos"));
        assertTrue(palindroom.isPalindroom("palindroommoordnilap"));
    }

    @Test
    public void palindroomMetbijzondereCharacters() {
        Palindroom palindroom = new Palindroom();
        assertFalse(palindroom.isPalindroom("()()"));
        assertTrue(palindroom.isPalindroom("())("));
        assertTrue(palindroom.isPalindroom("123321"));
    }

    @Test
    public void legeStringGaatOokGoed() {
        Palindroom palindroom = new Palindroom();
        assertTrue(palindroom.isPalindroom(""));
    }
}
