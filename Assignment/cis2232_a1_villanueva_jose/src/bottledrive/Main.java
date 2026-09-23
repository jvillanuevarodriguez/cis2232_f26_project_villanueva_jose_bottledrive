package bottledrive;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<BottleDonation> donations = FileManager.loadDonations();
        String option;

        do {
            System.out.println();
            System.out.println("Bottle Drive Log");
            System.out.println("A) Add");
            System.out.println("V) View");
            System.out.println("X) Exit");
            System.out.print("Select an option: ");

            option = scanner.nextLine().trim().toUpperCase();

            switch (option) {
                case "A":
                    System.out.println();
                    System.out.println("Add New Donation");

                    System.out.print("Depositor name: ");
                    String depositorName = scanner.nextLine();

                    System.out.print("Donation date (yyyy-MM-dd): ");
                    String donationDate = scanner.nextLine();

                    int smallContainerCount = readInt(scanner, "Small container count: ");
                    int largeContainerCount = readInt(scanner, "Large container count: ");
                    double smallContainerRate = readDouble(scanner, "Small container rate: ");
                    double largeContainerRate = readDouble(scanner, "Large container rate: ");

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    int id = donations.size() + 1;
                    String createdDateTime = LocalDateTime.now().toString();

                    BottleDonation donation = new BottleDonation(
                            id,
                            createdDateTime,
                            depositorName,
                            donationDate,
                            smallContainerCount,
                            largeContainerCount,
                            smallContainerRate,
                            largeContainerRate,
                            notes
                    );

                    donations.add(donation);
                    FileManager.saveDonations(donations);

                    System.out.println("Donation added successfully.");
                    break;


                case "V":
                    System.out.println();
                    System.out.println("Bottle Drive Donations");

                    if (donations.isEmpty()) {
                        System.out.println("No donations have been added.");
                    } else {
                        for (BottleDonation item : donations) {
                            System.out.println();
                            System.out.println("ID: " + item.getId());
                            System.out.println("Created: " + item.getCreatedDateTime());
                            System.out.println("Depositor: " + item.getDepositorName());
                            System.out.println("Donation Date: " + item.getDonationDate());
                            System.out.println("Small Containers: " + item.getSmallContainerCount());
                            System.out.println("Large Containers: " + item.getLargeContainerCount());
                            System.out.println("Small Container Rate: " + item.getSmallContainerRate());
                            System.out.println("Large Container Rate: " + item.getLargeContainerRate());
                            System.out.println("Notes: " + item.getNotes());
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case "X":
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option. Please choose A, V, or X.");
            }

        } while (!option.equals("X"));

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (example: 0.10).");
            }
        }
    }
}
