package codefromvideo.lifecycle;

import codefromvideo.junit.Palindroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class PalindroomTestMetSetup {

    private Palindroom palindroom;

    @BeforeEach
    public void setup() {
        palindroom = new Palindroom();
    }

    @ParameterizedTest(name = "De string \"{0}\" is een palindroom: {1}")
    @CsvSource({
            "abba, true",
            "sos, true",
            "'', true",
            "abcde, false"
    })
    public void parameterizedTest(String palindroomVoorbeeld, boolean isPalindroom) {
        assertEquals(isPalindroom, palindroom.isPalindroom(palindroomVoorbeeld));
    }

    @Test
    public void geldigePalindroom() {
        assertTrue(palindroom.isPalindroom("abba"));
        assertTrue(palindroom.isPalindroom("sos"));
        assertTrue(palindroom.isPalindroom("palindroommoordnilap"));
    }

    @Test
    public void palindroomMetbijzondereCharacters() {
        assertFalse(palindroom.isPalindroom("()()"));
        assertTrue(palindroom.isPalindroom("())("));
        assertTrue(palindroom.isPalindroom("123321"));
    }

    @Test
    public void legeStringGaatOokGoed() {
        assertTrue(palindroom.isPalindroom(""));
    }
}
