package codefromvideo.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
public class WinkelmandTest {

    Winkelmand winkelmand;

    @BeforeEach
    public void init() {
        winkelmand = new Winkelmand();
    }

    // Mock (nog zonder gedrag)
    @Test
    public void testToevoegenProduct() {
        Sessie sessie = mock(Sessie.class);
        Product product  = new Product("Effective Java");
        winkelmand.voegProductToeAanWinkelmand(sessie, product);
    }

    // Mock (inclusief gewenst gedrag)
    @Test
    public void testToevoegenProductNogNietInLijst() {
        Sessie sessie = mock(Sessie.class);
        Product product  = new Product("Effective Java");
        assertTrue(winkelmand.voegProductToeAanWinkelmand(sessie, product));

    }

    // Mock (inclusief gewenst gedrag)
    @Test
    public void testToevoegenProductZitAlInLijst() {
        // Maak mock
        Sessie sessie = mock(Sessie.class);

        // Testdata
        Product product = new Product("Effective Java");
        Product product2 = new Product("Effective Java");
        List<Product> inhoudWinkelmand = Arrays.asList(product2);

        // Vertel mock om testdata terug te geven
        when(sessie.getProducten()).thenReturn(inhoudWinkelmand);

        assertFalse(
                winkelmand.voegProductToeAanWinkelmand(sessie, product)
        );
    }
}
