package bottledrive;

public class BottleDonation {

    private int id;
    private String createdDateTime;
    private String depositorName;
    private String donationDate;
    private int smallContainerCount;
    private int largeContainerCount;
    private double smallContainerRate;
    private double largeContainerRate;
    private String notes;

    public BottleDonation() {
    }

    public BottleDonation(int id, String createdDateTime, String depositorName,
                          String donationDate, int smallContainerCount,
                          int largeContainerCount, double smallContainerRate,
                          double largeContainerRate, String notes) {

        this.id = id;
        this.createdDateTime = createdDateTime;
        this.depositorName = depositorName;
        this.donationDate = donationDate;
        this.smallContainerCount = smallContainerCount;
        this.largeContainerCount = largeContainerCount;
        this.smallContainerRate = smallContainerRate;
        this.largeContainerRate = largeContainerRate;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public int getSmallContainerCount() {
        return smallContainerCount;
    }

    public int getLargeContainerCount() {
        return largeContainerCount;
    }

    public double getSmallContainerRate() {
        return smallContainerRate;
    }

    public double getLargeContainerRate() {
        return largeContainerRate;
    }

    public String getNotes() {
        return notes;
    }
}