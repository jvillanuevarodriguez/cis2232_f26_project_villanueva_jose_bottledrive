package info.hccis.performance.controllers;

import info.hccis.performance.dao.BottleDonationDAO;
import info.hccis.performance.entity.ReportDonation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import info.hccis.performance.util.ReportFileUtility;
import java.io.IOException;

@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger =
            LoggerFactory.getLogger(ReportController.class);

    @GetMapping("")
    public String home() {
        return "report/list";
    }

    @GetMapping("/donation/date")
    public String reportByDate(Model model) {
        model.addAttribute("reportInput", new ReportDonation());
        return "report/reportDonationDate";
    }

    @PostMapping("/donation/date/submit")
    public String reportByDateSubmit(
            Model model,
            @ModelAttribute("reportInput") ReportDonation report) {

        try {
            LocalDate start = LocalDate.parse(report.getDateStart());
            LocalDate end = LocalDate.parse(report.getDateEnd());

            if (start.isAfter(end)) {
                model.addAttribute("message",
                        "The start date must be before or equal to the end date.");
                return "report/reportDonationDate";
            }

            BottleDonationDAO dao = new BottleDonationDAO();
            report.setDonations(
                    dao.selectDonations(start.toString(), end.toString()));
            try {
                ReportFileUtility.saveReport("DonationsByDate", report);
            } catch (IOException e) {
                logger.error("Could not save the date report.", e);
                model.addAttribute("fileMessage",
                        "The report loaded, but the text file could not be saved.");
            }
            if (report.getDonations().isEmpty()) {
                model.addAttribute("message", "No donations found.");
            }

        } catch (DateTimeParseException | NullPointerException e) {
            model.addAttribute("message",
                    "Please enter a valid start date and end date.");
        } catch (SQLException e) {
            logger.error("Could not load donations by date.", e);
            model.addAttribute("message",
                    "The report could not be loaded. Please try again.");
        }

        return "report/reportDonationDate";
    }

    @GetMapping("/donation/depositor")
    public String reportByDepositor(Model model) {
        model.addAttribute("reportInput", new ReportDonation());
        return "report/reportDonationDepositor";
    }

    @PostMapping("/donation/depositor/submit")
    public String reportByDepositorSubmit(
            Model model,
            @ModelAttribute("reportInput") ReportDonation report) {

        String name = report.getDepositorName();

        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("message", "Please enter a depositor name.");
            return "report/reportDonationDepositor";
        }

        report.setDepositorName(name.trim());

        try {
            BottleDonationDAO dao = new BottleDonationDAO();
            report.setDonations(
                    dao.selectDonations(report.getDepositorName()));
            try {
                ReportFileUtility.saveReport("DonationsByDepositor", report);
            } catch (IOException e) {
                logger.error("Could not save the depositor report.", e);
                model.addAttribute("fileMessage",
                        "The report loaded, but the text file could not be saved.");
            }
            if (report.getDonations().isEmpty()) {
                model.addAttribute("message", "No donations found.");
            }

        } catch (SQLException e) {
            logger.error("Could not load donations by depositor.", e);
            model.addAttribute("message",
                    "The report could not be loaded. Please try again.");
        }

        return "report/reportDonationDepositor";
    }
}