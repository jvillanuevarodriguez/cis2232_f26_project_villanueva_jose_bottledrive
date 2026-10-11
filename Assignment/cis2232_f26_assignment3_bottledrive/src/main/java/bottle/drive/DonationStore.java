package bottle.drive;

import bottle.drive.bo.BottleDonationBO;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Holds the donations shared by the console and dialog threads.
 *
 * @author Jose Villanueva
 */
public class DonationStore {

    private final ArrayList<BottleDonation> donations;
    private final BottleDonationBO donationBO = new BottleDonationBO();


    public DonationStore() {
        donations = FileManager.loadDonations();
    }

    public synchronized BottleDonation addDonation(
            String depositorName,
            String donationDate,
            int smallCount,
            int largeCount,
            double smallRate,
            double largeRate,
            String notes) {

        if (depositorName == null || depositorName.isBlank()) {
            throw new IllegalArgumentException(
                    "Depositor name is required."
            );
        }

        int nextId = 1;

        for (BottleDonation existing : donations) {
            nextId = Math.max(nextId, existing.getId() + 1);
        }

        BottleDonation donation = new BottleDonation(
                nextId,
                LocalDateTime.now().toString(),
                depositorName.trim(),
                donationDate,
                smallCount,
                largeCount,
                smallRate,
                largeRate,
                notes
        );


        donationBO.calculate(donation);

        ArrayList<BottleDonation> updatedDonations =
                new ArrayList<>(donations);

        updatedDonations.add(donation);


        FileManager.saveDonations(updatedDonations);

        donations.add(donation);

        return donation;
    }

    /**
     * Returns a copy of the accumulated donations.
     *
     * @return a snapshot of the shared donation records
     */
    public synchronized ArrayList<BottleDonation> getDonations() {
        return new ArrayList<>(donations);
    }

    /**
     * Returns the combined number of saved donations.
     * This count includes entries from both application threads
     * and entries loaded from previous runs.
     *
     * @return the accumulated donation count
     */
    public synchronized int getDonationCount() {
        return donations.size();
    }
}