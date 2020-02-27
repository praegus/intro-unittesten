package unit.test.training.auth;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String DEFAULT_ROLE_NAME = "UNITTESTUSER";

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // hard coding the users. All passwords must be encoded.
        final List<unit.test.training.dto.User> users = Arrays.asList(
                unit.test.training.dto.User.UserBuilder.withValues(1, "Bas", "Stoker", "bas@bas.st","123"),
                unit.test.training.dto.User.UserBuilder.withValues(1, "Abel", "Stoker",  "abel@gmail.com", "456")
        );

        for(unit.test.training.dto.User appUser: users) {
            if(appUser.getEmail().equals(username)) {
                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + DEFAULT_ROLE_NAME);

                for (GrantedAuthority ga: grantedAuthorities) {
                    System.out.println(ga.getAuthority());
                }

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new User(appUser.getEmail(), encoder.encode(appUser.getPassword()), grantedAuthorities);
            }
        }

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}