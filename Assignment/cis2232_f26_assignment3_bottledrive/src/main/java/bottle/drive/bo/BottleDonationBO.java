package bottle.drive.bo;

import bottle.drive.BottleDonation;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides the business calculations for bottle donations.
 */
public class BottleDonationBO {

    private static final int CURRENCY_SCALE = 2;

    /**
     * Calculates the total refund for a donation.
     * Each container-type refund is rounded to two decimal places
     * using HALF_UP before the amounts are added.
     *
     * @param donation the donation containing the counts and rates
     * @return the combined refund for both container types
     * @throws IllegalArgumentException if the donation is null,
     *         a count is negative, both counts are zero,
     *         or a rate is non-finite or not greater than zero
     */
    public double calculate(BottleDonation donation) {

        if (donation == null) {
            throw new IllegalArgumentException(
                    "Donation cannot be null."
            );
        }

        int smallCount = donation.getSmallContainerCount();
        int largeCount = donation.getLargeContainerCount();

        double smallRate = donation.getSmallContainerRate();
        double largeRate = donation.getLargeContainerRate();

        if (smallCount < 0 || largeCount < 0) {
            throw new IllegalArgumentException(
                    "Container counts cannot be negative."
            );
        }

        if (smallCount == 0 && largeCount == 0) {
            throw new IllegalArgumentException(
                    "A donation must contain at least one container."
            );
        }

        if (!Double.isFinite(smallRate) || smallRate <= 0) {
            throw new IllegalArgumentException(
                    "Small container rate must be finite and greater than zero."
            );
        }

        if (!Double.isFinite(largeRate) || largeRate <= 0) {
            throw new IllegalArgumentException(
                    "Large container rate must be finite and greater than zero."
            );
        }

        BigDecimal smallRefund = BigDecimal.valueOf(smallRate)
                .multiply(BigDecimal.valueOf(smallCount))
                .setScale(CURRENCY_SCALE, RoundingMode.HALF_UP);

        BigDecimal largeRefund = BigDecimal.valueOf(largeRate)
                .multiply(BigDecimal.valueOf(largeCount))
                .setScale(CURRENCY_SCALE, RoundingMode.HALF_UP);

        return smallRefund.add(largeRefund).doubleValue();
    }
}