package vakantieplanner.dto;

public class ReserveRequest {
    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    private String dateRange;

    @Override
    public String toString() {
        return "ReserveRequest{" +
                "dateRange='" + dateRange + '\'' +
                '}';
    }
}
