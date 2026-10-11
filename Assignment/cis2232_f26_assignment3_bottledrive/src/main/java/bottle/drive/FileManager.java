package bottle.drive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Reads and writes bottle donations using a shared JSON file.
 *
 * @author Jose Villanueva
 */
public class FileManager {

    private static final String DIRECTORY = "C:\\cis2232";

    private static final String FILE_PATH =
            DIRECTORY + "\\data_villanueva_jose.json";

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Saves the supplied donations.
     * Only one thread can access this synchronized file operation
     * at a time.
     *
     * @param donations the donations to save
     * @throws IllegalStateException if the file cannot be saved
     */
    public static synchronized void saveDonations(
            ArrayList<BottleDonation> donations) {

        ensureDirectoryExists();

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            GSON.toJson(donations, writer);
        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Could not save donations.",
                    exception
            );
        }
    }

    /**
     * Loads the donations from the shared file.
     *
     * @return the saved donations, or an empty list if no file exists
     * @throws IllegalStateException if the file cannot be read
     *         or contains invalid JSON
     */
    public static synchronized ArrayList<BottleDonation> loadDonations() {

        ensureDirectoryExists();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<BottleDonation>>() {
                    }.getType();

            ArrayList<BottleDonation> donations =
                    GSON.fromJson(reader, listType);

            return donations == null
                    ? new ArrayList<>()
                    : donations;

        } catch (IOException | JsonParseException exception) {
            throw new IllegalStateException(
                    "Could not load donations. Check the data file.",
                    exception
            );
        }
    }

    /**
     * Creates the data directory when necessary.
     *
     * @throws IllegalStateException if the directory cannot be created
     */
    private static void ensureDirectoryExists() {

        File directory = new File(DIRECTORY);

        if (!directory.isDirectory()
                && !directory.mkdirs()
                && !directory.isDirectory()) {

            throw new IllegalStateException(
                    "Could not create the data directory: " + DIRECTORY
            );
        }
    }
}