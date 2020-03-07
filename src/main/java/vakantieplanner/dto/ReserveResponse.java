package vakantieplanner.dto;

public class ReserveResponse {
    private boolean result;

    private User blockingUser;

    public boolean reserveringIsGoedgekeurd() {
        return result;
    }

    public boolean reserveringIsAfgekeurd() {
        return !result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setBlockingUser(User blockingUser) {
        this.blockingUser = blockingUser;
    }

    public User getBlockingUser() {
        return blockingUser;
    }
}
