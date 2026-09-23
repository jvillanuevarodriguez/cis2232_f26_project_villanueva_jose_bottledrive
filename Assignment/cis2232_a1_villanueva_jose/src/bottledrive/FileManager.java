package bottledrive;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileManager {

    private static final String DIRECTORY = "C:\\cis2232";
    private static final String FILE_PATH =
            DIRECTORY + "\\data_villanueva_jose.json";

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void saveDonations(ArrayList<BottleDonation> donations) {

        File directory = new File(DIRECTORY);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(donations, writer);

        } catch (IOException e) {
            System.out.println("Error saving donations: " + e.getMessage());
        }
    }

    public static ArrayList<BottleDonation> loadDonations() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {

            Type listType =
                    new TypeToken<ArrayList<BottleDonation>>() {}.getType();

            ArrayList<BottleDonation> donations =
                    gson.fromJson(reader, listType);

            if (donations == null) {
                return new ArrayList<>();
            }

            return donations;

        } catch (IOException e) {
            System.out.println("Error loading donations: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}