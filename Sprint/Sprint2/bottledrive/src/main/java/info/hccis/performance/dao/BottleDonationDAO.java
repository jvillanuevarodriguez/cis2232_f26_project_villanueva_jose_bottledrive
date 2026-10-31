package info.hccis.performance.dao;

import info.hccis.performance.entity.BottleDonation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BottleDonationDAO {

    private Connection getConnection() throws SQLException {
        ResourceBundle settings = ResourceBundle.getBundle("application");

        return DriverManager.getConnection(
                settings.getString("spring.datasource.url"),
                settings.getString("spring.datasource.username"),
                settings.getString("spring.datasource.password")
        );
    }

    // Get donations between the selected dates, including both dates.
    public ArrayList<BottleDonation> selectDonations(
            String start, String end) throws SQLException {

        ArrayList<BottleDonation> donations = new ArrayList<>();

        String query = "SELECT * FROM bottle_donation "
                + "WHERE donationDate BETWEEN ? AND ? "
                + "ORDER BY donationDate, id";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, start);
            statement.setString(2, end);

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    donations.add(readDonation(results));
                }
            }
        }

        return donations;
    }

    // Get donations for the selected depositor.
    public ArrayList<BottleDonation> selectDonations(
            String depositorName) throws SQLException {

        ArrayList<BottleDonation> donations = new ArrayList<>();

        String query = "SELECT * FROM bottle_donation "
                + "WHERE depositorName = ? "
                + "ORDER BY donationDate, id";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, depositorName.trim());

            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    donations.add(readDonation(results));
                }
            }
        }

        return donations;
    }

    // Copy one database row into a donation object.
    private BottleDonation readDonation(ResultSet results)
            throws SQLException {

        BottleDonation donation = new BottleDonation();

        donation.setId(results.getInt("id"));
        donation.setCreatedDateTime(results.getString("createdDateTime"));
        donation.setDepositorName(results.getString("depositorName"));
        donation.setDonationDate(results.getString("donationDate"));
        donation.setSmallContainerCount(
                results.getInt("smallContainerCount"));
        donation.setLargeContainerCount(
                results.getInt("largeContainerCount"));
        donation.setSmallContainerRate(
                results.getBigDecimal("smallContainerRate"));
        donation.setLargeContainerRate(
                results.getBigDecimal("largeContainerRate"));
        donation.setSmallRefund(results.getBigDecimal("smallRefund"));
        donation.setLargeRefund(results.getBigDecimal("largeRefund"));
        donation.setTotalRefund(results.getBigDecimal("totalRefund"));
        donation.setNotes(results.getString("notes"));

        return donation;
    }
}