package codefromvideo.junitcompleet;

import codefromvideo.junit.Palindroom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PalindroomTestMetSetup {

    private Palindroom palindroom;

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
