package info.hccis.performance.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bottle_donation")
public class BottleDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "createdDateTime", insertable = false, updatable = false)
    private String createdDateTime;

    @NotBlank(message = "Depositor name is required.")
    @Column(name = "depositorName", nullable = false)
    private String depositorName;

    @NotBlank(message = "Donation date is required.")
    @Column(name = "donationDate", nullable = false)
    private String donationDate;

    @Min(value = 0, message = "Small container count must be zero or greater.")
    @Column(name = "smallContainerCount", nullable = false)
    private int smallContainerCount;

    @Min(value = 0, message = "Large container count must be zero or greater.")
    @Column(name = "largeContainerCount", nullable = false)
    private int largeContainerCount;

    @NotNull(message = "Small container rate is required.")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Small container rate must be greater than zero.")
    @Column(name = "smallContainerRate", nullable = false)
    private BigDecimal smallContainerRate;

    @NotNull(message = "Large container rate is required.")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Large container rate must be greater than zero.")
    @Column(name = "largeContainerRate", nullable = false)
    private BigDecimal largeContainerRate;

    @Column(name = "smallRefund")
    private BigDecimal smallRefund;

    @Column(name = "largeRefund")
    private BigDecimal largeRefund;

    @Column(name = "totalRefund")
    private BigDecimal totalRefund;

    @Column(name = "notes")
    private String notes;

    public BottleDonation() {
    }

    @AssertTrue(message = "At least one container count must be greater than zero.")
    public boolean isAtLeastOneContainer() {
        return smallContainerCount > 0 || largeContainerCount > 0;
    }

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