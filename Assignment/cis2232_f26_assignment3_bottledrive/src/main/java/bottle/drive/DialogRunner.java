package bottle.drive;

import bottle.drive.bo.BottleDonationBO;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Supplier;

/**
 * Runs the bottle donation application using JOptionPane dialogs.
 *
 * @author Jose Villanueva
 */
public class DialogRunner implements Runnable {

    private final DonationStore store;
    private final BottleDonationBO donationBO = new BottleDonationBO();

    public DialogRunner(DonationStore store) {
        this.store = store;
    }

    @Override
    public void run() {

        boolean running = true;

        while (running) {

            String option = onEventThread(() ->
                    JOptionPane.showInputDialog(
                            null,
                            "A) Add donation\n"
                                    + "V) View donations\n"
                                    + "X) Exit dialogs\n\n"
                                    + "Choose an option:",
                            "Bottle Drive - Dialogs",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );

            if (option == null) {
                break;
            }

            try {
                switch (option.trim().toUpperCase()) {
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
                        showMessage("Please choose A, V or X.");
                }
            } catch (CancellationException exception) {
                showMessage("Donation cancelled. Nothing was saved.");
            } catch (IllegalArgumentException | IllegalStateException exception) {
                showMessage("Error: " + exception.getMessage());
            }
        }

        showMessage("Dialog workflow finished.");
    }

    private void addDonation() {

        String depositorName = readText("Depositor name:");
        String donationDate = readText("Donation date (yyyy-MM-dd):");

        int smallCount = readInt("Number of small containers:");
        int largeCount = readInt("Number of large containers:");

        double smallRate = readDouble("Small container rate:");
        double largeRate = readDouble("Large container rate:");

        String notes = readText("Notes (optional):");

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

        showMessage(String.format(
                "Donation saved. ID: %d%n"
                        + "Total refund: $%.2f%n"
                        + "Combined donation count: %d",
                donation.getId(),
                refund,
                store.getDonationCount()
        ));
    }
    private void viewDonations() {

        ArrayList<BottleDonation> donations = store.getDonations();

        StringBuilder text = new StringBuilder();

        text.append("DONATIONS FROM BOTH INTERFACES\n");
        text.append("Combined donation count: ");
        text.append(donations.size());
        text.append("\n");

        if (donations.isEmpty()) {
            text.append("\nNo donations recorded.");
        }

        for (BottleDonation donation : donations) {

            text.append("\nID: ").append(donation.getId());
            text.append("\nCreated: ")
                    .append(donation.getCreatedDateTime());
            text.append("\nDepositor: ")
                    .append(donation.getDepositorName());
            text.append("\nDonation date: ")
                    .append(donation.getDonationDate());
            text.append("\nSmall containers: ")
                    .append(donation.getSmallContainerCount());
            text.append("\nLarge containers: ")
                    .append(donation.getLargeContainerCount());

            text.append(String.format(
                    "%nSmall container rate: $%.2f"
                            + "%nLarge container rate: $%.2f"
                            + "%nTotal refund: $%.2f",
                    donation.getSmallContainerRate(),
                    donation.getLargeContainerRate(),
                    donationBO.calculate(donation)
            ));

            text.append("\nNotes: ")
                    .append(donation.getNotes());
            text.append("\n");
        }

        onEventThread(() -> {

            JTextArea area = new JTextArea(text.toString(), 20, 50);
            area.setEditable(false);
            area.setCaretPosition(0);

            JOptionPane.showMessageDialog(
                    null,
                    new JScrollPane(area),
                    "Bottle Drive - Donations",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return null;
        });
    }

    private String readText(String prompt) {

        String input = onEventThread(() ->
                JOptionPane.showInputDialog(
                        null,
                        prompt,
                        "Bottle Drive - Add Donation",
                        JOptionPane.QUESTION_MESSAGE
                )
        );

        if (input == null) {
            throw new CancellationException();
        }

        return input;
    }

    private int readInt(String prompt) {

        while (true) {

            String input = readText(prompt);

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException exception) {
                showMessage("Please enter a whole number.");
            }
        }
    }

    private double readDouble(String prompt) {

        while (true) {

            String input = readText(prompt);

            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException exception) {
                showMessage(
                        "Please enter a number using a decimal point."
                );
            }
        }
    }

    private void showMessage(String message) {

        onEventThread(() -> {
            JOptionPane.showMessageDialog(
                    null,
                    message,
                    "Bottle Drive - Dialogs",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return null;
        });
    }

    private <T> T onEventThread(Supplier<T> action) {

        if (SwingUtilities.isEventDispatchThread()) {
            return action.get();
        }

        FutureTask<T> task = new FutureTask<>(action::get);
        SwingUtilities.invokeLater(task);

        try {
            return task.get();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Dialog workflow was interrupted.",
                    exception
            );
        } catch (ExecutionException exception) {
            throw new IllegalStateException(
                    "Could not display the dialog.",
                    exception.getCause()
            );
        }
    }
}