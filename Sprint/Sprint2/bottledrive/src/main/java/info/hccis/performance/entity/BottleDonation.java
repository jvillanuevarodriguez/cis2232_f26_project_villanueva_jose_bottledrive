package info.hccis.performance.entity;

import java.math.BigDecimal;

public class BottleDonation {

    private int id;
    private String createdDateTime;
    private String depositorName;
    private String donationDate;
    private int smallContainerCount;
    private int largeContainerCount;
    private BigDecimal smallContainerRate;
    private BigDecimal largeContainerRate;
    private BigDecimal smallRefund;
    private BigDecimal largeRefund;
    private BigDecimal totalRefund;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public int getSmallContainerCount() {
        return smallContainerCount;
    }

    public void setSmallContainerCount(int smallContainerCount) {
        this.smallContainerCount = smallContainerCount;
    }

    public int getLargeContainerCount() {
        return largeContainerCount;
    }

    public void setLargeContainerCount(int largeContainerCount) {
        this.largeContainerCount = largeContainerCount;
    }

    public BigDecimal getSmallContainerRate() {
        return smallContainerRate;
    }

    public void setSmallContainerRate(BigDecimal smallContainerRate) {
        this.smallContainerRate = smallContainerRate;
    }

    public BigDecimal getLargeContainerRate() {
        return largeContainerRate;
    }

    public void setLargeContainerRate(BigDecimal largeContainerRate) {
        this.largeContainerRate = largeContainerRate;
    }

    public BigDecimal getSmallRefund() {
        return smallRefund;
    }

    public void setSmallRefund(BigDecimal smallRefund) {
        this.smallRefund = smallRefund;
    }

    public BigDecimal getLargeRefund() {
        return largeRefund;
    }

    public void setLargeRefund(BigDecimal largeRefund) {
        this.largeRefund = largeRefund;
    }

    public BigDecimal getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(BigDecimal totalRefund) {
        this.totalRefund = totalRefund;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}