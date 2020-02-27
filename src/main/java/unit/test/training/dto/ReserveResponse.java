package unit.test.training.dto;

public class ReserveResponse {
    private boolean result;

    private User blockingUser;

    public boolean isResult() {
        return result;
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
