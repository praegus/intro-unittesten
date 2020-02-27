package unit.test.training.database;

import org.springframework.stereotype.Component;
import unit.test.training.dto.User;

import java.util.Arrays;
import java.util.List;

@Component
public class DataAccessLayerImpl implements DataAccessLayer {
    @Override
    public List<User> getUsers() {
        return Arrays.asList(
                User.UserBuilder.withValues(1, "Jasper", "Laboyrie", "jasper.laboyrie@praegus.nl"),
                User.UserBuilder.withValues(2, "Martijn", "de Ruijter", "martijn.de.ruijter@praegus.nl"),
                User.UserBuilder.withValues(3, "Maarten-Jan", "van Gool", "maarten-jan.van.gool@praegus.nl"),
                User.UserBuilder.withValues(4, "Xue Qin", "Chow", "xueqin.chow@praegus.nl"),
                User.UserBuilder.withValues(5, "Marcel", "Hogenhout", "marcel.hogenhout@praegus.nl"),
                User.UserBuilder.withValues(6, "Marcel", "Gool", "marcel.gool@praegus.nl"),
                User.UserBuilder.withValues(7, "Ywe", "van der Pol", "ywe.van.der.pol@praegus.nl"),
                User.UserBuilder.withValues(8, "Michael", "van Splunter", "michael.van.splunter@praegus.nl"),
                User.UserBuilder.withValues(9, "Fin", "Kingma", "fin.kingma@praegus.nl"),
                User.UserBuilder.withValues(10, "Margreet", "Hoekstra-Rietberg", "margreet.hoekstra-rietberg@praegus.nl"),
                User.UserBuilder.withValues(11, "Timo", "Deriga", "Timo.Deriga@praegus.nl"),
                User.UserBuilder.withValues(12, "Bas", "Vegter", "Bas.vegter@praegus.nl"),
                User.UserBuilder.withValues(13, "Thimoty", "Wijsbek", "thimoty.wijsbek@praegus.nl"),
                User.UserBuilder.withValues(14, "Jochem", "Rasche", "jochem.rasche@praegus.nl"),
                User.UserBuilder.withValues(15, "Juriaan", "Rijnaars", "juriaan.rijnaars@praegus.nl"),
                User.UserBuilder.withValues(16, "Alfred", "Hol", "Alfred.hol@praegus.nl"),
                User.UserBuilder.withValues(17, "Danny", "Buffing", "danny.buffing@praegus.nl"),
                User.UserBuilder.withValues(18, "Eibert", "Dijkgraaf", "eibert.dijkgraaf@praegus.nl"),
                User.UserBuilder.withValues(19, "Nicolai", "Roos", "nicolai.roos@praegus.nl"),
                User.UserBuilder.withValues(20, "Jan-Willem", "van Putte", "jan-willem.van.putte@praegus.nl"),
                User.UserBuilder.withValues(21, "Edgar", "Backer", "edgar.backer@praegus.nl"),
                User.UserBuilder.withValues(22, "Ingmar", "van Oostrum", "ingmar.van.oostrum@praegus.nl"),
                User.UserBuilder.withValues(23, "Geert", "Gordebeke", "geert.gordebeke@praegus.nl"),
                User.UserBuilder.withValues(24, "Frank", "van Dronkelaar", "frank.van.dronkelaar@praegus.nl"),
                User.UserBuilder.withValues(25, "John", "Simons", "john.simons@praegus.nl"),
                User.UserBuilder.withValues(26, "Lennard", "de Groot", "lennard.de.groot@praegus.nl")
        );

    }
}