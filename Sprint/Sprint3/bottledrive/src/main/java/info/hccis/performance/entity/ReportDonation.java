package info.hccis.performance.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ReportDonation {

    private String dateStart;
    private String dateEnd;
    private String depositorName;
    private ArrayList<BottleDonation> donations = new ArrayList<>();

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public ArrayList<BottleDonation> getDonations() {
        return donations;
    }

    public void setDonations(ArrayList<BottleDonation> donations) {
        this.donations = donations;
    }

    public BigDecimal getTotalRefund() {
        BigDecimal total = new BigDecimal("0.00");

        for (BottleDonation donation : donations) {
            total = total.add(donation.getTotalRefund());
        }

        return total;
    }
    public Map<String, BigDecimal> getDailyTotals() {
        Map<String, BigDecimal> dailyTotals = new TreeMap<>();

        for (BottleDonation donation : donations) {
            String date = donation.getDonationDate();
            BigDecimal total = dailyTotals.getOrDefault(
                    date, BigDecimal.ZERO);

            dailyTotals.put(date, total.add(donation.getTotalRefund()));
        }

        return dailyTotals;
    }
}