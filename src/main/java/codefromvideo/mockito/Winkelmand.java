package codefromvideo.mockito;

import java.util.List;

public class Winkelmand {

    public boolean voegProductToeAanWinkelmand(
            Sessie sessie,
            Product nieuwProduct) {
        List<Product> producten = sessie.getProducten();

        if (productZitNogNietInLijst(nieuwProduct, producten)) {
            producten.add(nieuwProduct);
            sessie.setProducten(producten);
            return true;
        } else {
            // product mag maar 1 keer in winkelmand zitten
            return false;
        }
    }

    private boolean productZitNogNietInLijst(Product nieuwProduct, List<Product> producten) {
        return producten.stream().noneMatch(p -> p.getNaam().equals(nieuwProduct.getNaam()));
    }
}
