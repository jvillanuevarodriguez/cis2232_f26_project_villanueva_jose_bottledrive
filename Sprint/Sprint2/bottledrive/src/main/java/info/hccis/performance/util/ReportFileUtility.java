package info.hccis.performance.util;

import info.hccis.performance.entity.BottleDonation;
import info.hccis.performance.entity.ReportDonation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportFileUtility {

    public static Path saveReport(String reportType,
                                  ReportDonation report) throws IOException {

        Path folder = Paths.get("C:\\cis2232");
        Files.createDirectories(folder);

        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        Path file = Files.createTempFile(
                folder, reportType + "_" + timestamp + "_", ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(
                file, StandardCharsets.UTF_8)) {

            writer.write("Bottle Drive Log - " + reportType);
            writer.newLine();
            writer.write("Generated: " + now);
            writer.newLine();

            if ("DonationsByDate".equals(reportType)) {
                writer.write("Start date: " + report.getDateStart());
                writer.newLine();
                writer.write("End date: " + report.getDateEnd());
            } else {
                writer.write("Depositor: " + report.getDepositorName());
            }

            writer.newLine();
            writer.newLine();

            writer.write("ID\tDate\tDepositor\tSmall count\tLarge count"
                    + "\tSmall rate\tLarge rate\tSmall refund"
                    + "\tLarge refund\tTotal refund");
            writer.newLine();

            for (BottleDonation donation : report.getDonations()) {
                writer.write(
                        donation.getId() + "\t"
                                + donation.getDonationDate() + "\t"
                                + donation.getDepositorName() + "\t"
                                + donation.getSmallContainerCount() + "\t"
                                + donation.getLargeContainerCount() + "\t"
                                + donation.getSmallContainerRate() + "\t"
                                + donation.getLargeContainerRate() + "\t"
                                + donation.getSmallRefund() + "\t"
                                + donation.getLargeRefund() + "\t"
                                + donation.getTotalRefund()
                );
                writer.newLine();
            }

            if (report.getDonations().isEmpty()) {
                writer.write("No donations found.");
                writer.newLine();
            }

            writer.newLine();
            writer.write("Total collected (CAD): $"
                    + report.getTotalRefund().toPlainString());
            writer.newLine();
        }

        return file;
    }
}