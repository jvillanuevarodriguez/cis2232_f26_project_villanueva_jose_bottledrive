package bottle.drive;

import bottle.drive.bo.BottleDonationBO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the bottle donation application entirely in the console.
 *
 * @author Jose Villanueva
 */
public class ConsoleRunner implements Runnable {

    private final DonationStore store;
    private final Scanner scanner = new Scanner(System.in);
    private final BottleDonationBO donationBO = new BottleDonationBO();


    public ConsoleRunner(DonationStore store) {
        this.store = store;
    }


    @Override
    public void run() {

        boolean running = true;

        while (running) {

            System.out.println("\nBOTTLE DRIVE - CONSOLE");
            System.out.println("A) Add donation");
            System.out.println("V) View donations");
            System.out.println("X) Exit console");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextLine()) {
                break;
            }

            String option = scanner.nextLine().trim().toUpperCase();

            try {
                switch (option) {
                    case "A":
                        addDonation();
                        break;
                    case "V":
                        viewDonations();
                        break;
                    case "X":
                        running = false;
                        break;
                    default:
                        System.out.println("Please choose A, V or X.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        System.out.println("Console workflow finished.");
    }


    private void addDonation() {

        System.out.print("Depositor name: ");
        String depositorName = scanner.nextLine();

        System.out.print("Donation date (yyyy-MM-dd): ");
        String donationDate = scanner.nextLine();

        int smallCount = readInt("Number of small containers: ");
        int largeCount = readInt("Number of large containers: ");

        double smallRate = readDouble("Small container rate: ");
        double largeRate = readDouble("Large container rate: ");

        System.out.print("Notes (optional): ");
        String notes = scanner.nextLine();

        BottleDonation donation = store.addDonation(
                depositorName,
                donationDate,
                smallCount,
                largeCount,
                smallRate,
                largeRate,
                notes
        );

        double refund = donationBO.calculate(donation);

        System.out.println("Donation saved. ID: " + donation.getId());
        System.out.printf("Total refund: $%.2f%n", refund);
        System.out.println(
                "Combined donation count: " + store.getDonationCount()
        );
    }

    private void viewDonations() {

        ArrayList<BottleDonation> donations = store.getDonations();

        System.out.println("\nDONATIONS FROM BOTH INTERFACES");
        System.out.println("Combined donation count: " + donations.size());

        if (donations.isEmpty()) {
            System.out.println("No donations recorded.");
            return;
        }

        for (BottleDonation donation : donations) {

            System.out.println("\nID: " + donation.getId());
            System.out.println(
                    "Created: " + donation.getCreatedDateTime()
            );
            System.out.println(
                    "Depositor: " + donation.getDepositorName()
            );
            System.out.println(
                    "Donation date: " + donation.getDonationDate()
            );
            System.out.println(
                    "Small containers: " + donation.getSmallContainerCount()
            );
            System.out.println(
                    "Large containers: " + donation.getLargeContainerCount()
            );

            System.out.printf(
                    "Small container rate: $%.2f%n",
                    donation.getSmallContainerRate()
            );
            System.out.printf(
                    "Large container rate: $%.2f%n",
                    donation.getLargeContainerRate()
            );
            System.out.printf(
                    "Total refund: $%.2f%n",
                    donationBO.calculate(donation)
            );

            System.out.println("Notes: " + donation.getNotes());
        }
    }

    private int readInt(String prompt) {

        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a whole number.");
            }
        }
    }


    private double readDouble(String prompt) {

        while (true) {
            System.out.print(prompt);

            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println(
                        "Please enter a number using a decimal point."
                );
            }
        }
    }
}