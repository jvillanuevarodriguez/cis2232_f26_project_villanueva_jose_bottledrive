package bottle.drive;

/**
 * Starts the console and dialog workflows in separate threads.
 *
 * @author Jose Villanueva
 */
public class Main {


    public static void main(String[] args) {

        DonationStore store;

        try {
            store = new DonationStore();
        } catch (IllegalStateException exception) {
            System.err.println(
                    "Could not start the application: "
                            + exception.getMessage()
            );
            return;
        }

        Thread consoleThread = new Thread(
                new ConsoleRunner(store),
                "BottleDrive-Console"
        );

        Thread dialogThread = new Thread(
                new DialogRunner(store),
                "BottleDrive-Dialogs"
        );

        consoleThread.start();
        dialogThread.start();

        try {
            consoleThread.join();
            dialogThread.join();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.err.println(
                    "The main thread was interrupted while waiting."
            );
            return;
        }

        System.out.println(
                "Both workflows have finished. Goodbye!"
        );
    }
}