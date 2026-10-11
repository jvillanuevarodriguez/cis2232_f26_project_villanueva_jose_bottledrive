package bottle.drive.bo;

import bottle.drive.BottleDonation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * AI-generated tests based on the Bottle Drive README
 * and the BottleDonation entity supplied to ChatGPT.
 */
class BottleDonationBOAITest {

    private final BottleDonationBO donationBO = new BottleDonationBO();

    @Test
    void calculateHandlesOnlyLargeContainers() {
        BottleDonation donation = createDonation(0, 4, 0.10, 0.25);

        assertEquals(1.00, donationBO.calculate(donation), 0.000001);
    }

    @Test
    void calculateUsesTheRatesFromTheDonation() {
        BottleDonation donation = createDonation(4, 2, 0.15, 0.30);

        assertEquals(1.20, donationBO.calculate(donation), 0.000001);
    }

    @Test
    void calculateReturnsTheRoundedCurrencyValue() {
        BottleDonation donation = createDonation(3, 0, 0.10, 0.25);

        // Zero tolerance intentionally checks the rounded numeric result.
        assertEquals(0.30, donationBO.calculate(donation), 0.0);
    }

    @Test
    void calculateRejectsNegativeLargeContainerCount() {
        BottleDonation donation = createDonation(2, -1, 0.10, 0.25);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    @Test
    void calculateRejectsDonationWithoutContainers() {
        BottleDonation donation = createDonation(0, 0, 0.10, 0.25);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    @Test
    void calculateRejectsZeroSmallContainerRate() {
        BottleDonation donation = createDonation(2, 1, 0.0, 0.25);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    @Test
    void calculateRejectsNegativeSmallContainerRate() {
        BottleDonation donation = createDonation(2, 1, -0.10, 0.25);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    @Test
    void calculateRejectsZeroLargeContainerRate() {
        BottleDonation donation = createDonation(2, 1, 0.10, 0.0);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    @Test
    void calculateRejectsNegativeLargeContainerRate() {
        BottleDonation donation = createDonation(2, 1, 0.10, -0.25);

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );
    }

    /**
     * Creates a donation with the values required by each test.
     *
     * @param smallCount number of small containers
     * @param largeCount number of large containers
     * @param smallRate refund rate for small containers
     * @param largeRate refund rate for large containers
     * @return a donation containing the supplied values
     */
    private BottleDonation createDonation(
            int smallCount,
            int largeCount,
            double smallRate,
            double largeRate) {

        return new BottleDonation(
                1,
                "2026-09-29T23:00:00",
                "Test Donor",
                "2026-09-29",
                smallCount,
                largeCount,
                smallRate,
                largeRate,
                ""
        );
    }
}