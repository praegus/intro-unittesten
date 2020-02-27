package codefromvideo.junitcompleet;

import codefromvideo.junit.Palindroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindroomTest {

    @Test
    public void abba() {
        Assertions.assertTrue(new Palindroom().isPalindroom("abba"));
    }

    @Test
    public void sos() {
        assertTrue(new Palindroom().isPalindroom("sos"));
    }

    @Test
    public void testBijzonderePalindroom() {
        assertFalse(new Palindroom().isPalindroom("()()"));
    }

    @Test
    public void anderePalindroomTest() {
        assertTrue(new Palindroom().isPalindroom("())("));
    }
}
