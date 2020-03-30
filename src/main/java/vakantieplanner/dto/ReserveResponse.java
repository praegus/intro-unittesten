package vakantieplanner.dto;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ReserveResponse {
    private boolean result;

    private User blockingUser;

    @JsonGetter
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
