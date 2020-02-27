package codefromvideo.junitcompleet;

import codefromvideo.junit.Palindroom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindroomTestMetBeforeClass {

    private Palindroom palindroom;

    @BeforeAll
    public static void setup() {
        // Lees bijv. groot testbestand in
    }

    @BeforeEach
    public void init() {
        palindroom = new Palindroom();
    }

    @Test
    public void abba() {
        assertTrue(palindroom.isPalindroom("abba"));
    }

    @Test
    public void sos() {
        assertTrue(palindroom.isPalindroom("sos"));
    }

    @Test
    public void testBijzonderePalindroom() {
        assertFalse(palindroom.isPalindroom("()()"));
    }

    @Test
    public void anderePalindroomTest() {
        assertTrue(palindroom.isPalindroom("())("));
    }
}
