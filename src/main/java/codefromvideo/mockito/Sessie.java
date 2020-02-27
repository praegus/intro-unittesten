package codefromvideo.mockito;

import java.util.List;

public interface Sessie {
    Klant getKlant();

    List<Product> getProducten();

    void setProducten(List<Product> producten);
}
