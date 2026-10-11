package bottle.drive.bo;

import bottle.drive.BottleDonation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BottleDonationBOTest {

    /**
     * TDD test 1: written before implementing the calculation.
     * Verifies the refund for a donation containing only small containers.
     */
    @Test

    void calculateReturnsRefundForSmallContainers() {

        BottleDonation donation = new BottleDonation(
                1,
                "2026-09-29T23:00:00",
                "Test Donor",
                "2026-09-29",
                3,
                0,
                0.10,
                0.25,
                ""
        );

        BottleDonationBO donationBO = new BottleDonationBO();

        double actual = donationBO.calculate(donation);

        assertEquals(0.30, actual, 0.000001);
    }

    /**
     * TDD test 2: written before adding the large-container calculation.
     * Verifies the total refund for a donation containing both types.
     */
    @Test
    void calculateReturnsTotalForBothContainerTypes() {

        BottleDonation donation = new BottleDonation(
                2,
                "2026-09-29T23:00:00",
                "Test Donor",
                "2026-09-29",
                3,
                2,
                0.10,
                0.25,
                ""
        );

        BottleDonationBO donationBO = new BottleDonationBO();

        double actual = donationBO.calculate(donation);

        assertEquals(0.80, actual, 0.000001);
    }

    /**
     * TDD test 3: written before implementing negative-count validation.
     * Verifies that the calculation rejects a negative container count.
     */
    @Test
    void calculateRejectsNegativeSmallContainerCount() {

        BottleDonation donation = new BottleDonation(
                3,
                "2026-09-29T23:00:00",
                "Test Donor",
                "2026-09-29",
                -1,
                2,
                0.10,
                0.25,
                ""
        );

        BottleDonationBO donationBO = new BottleDonationBO();

        assertThrows(
                IllegalArgumentException.class,
                () -> donationBO.calculate(donation)
        );

}

}
