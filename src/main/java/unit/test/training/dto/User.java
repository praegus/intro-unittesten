package unit.test.training.dto;

public class User {
    private int id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String firstname;
    private String lastname;
    private String password;

    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class UserBuilder {
        public static User withValues(int id, String firstname, String lastname, String email, String encodedPassword) {
            final User result = new User();
            result.setId(id);
            result.setFirstname(firstname);
            result.setLastname(lastname);
            result.setEmail(email);
            result.setPassword(encodedPassword);
            return result;
        }

        public static User withValues(int id, String firstname, String lastname, String email) {
            final User result = new User();
            result.setId(id);
            result.setFirstname(firstname);
            result.setLastname(lastname);
            result.setEmail(email);
            return result;
        }
    }
}
