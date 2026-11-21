package info.hccis.performance.controllers;

import info.hccis.performance.entity.BottleDonation;
import info.hccis.performance.repositories.BottleDonationRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bottledonation")
public class BottleDonationController {

    private final BottleDonationRepository repository;

    public BottleDonationController(BottleDonationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bottleDonation", new BottleDonation());
        model.addAttribute("bottleDonations", repository.findAll());
        return "bottledonation/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        BottleDonation donation = new BottleDonation();
        donation.setSmallContainerRate(new BigDecimal("0.10"));
        donation.setLargeContainerRate(new BigDecimal("0.25"));

        model.addAttribute("bottleDonation", donation);
        return "bottledonation/add";
    }

    @PostMapping("/submit")
    public String submit(
            @Valid @ModelAttribute("bottleDonation") BottleDonation donation,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "bottledonation/add";
        }

        BigDecimal smallRefund = donation.getSmallContainerRate()
                .multiply(BigDecimal.valueOf(donation.getSmallContainerCount()))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal largeRefund = donation.getLargeContainerRate()
                .multiply(BigDecimal.valueOf(donation.getLargeContainerCount()))
                .setScale(2, RoundingMode.HALF_UP);

        donation.setSmallRefund(smallRefund);
        donation.setLargeRefund(largeRefund);
        donation.setTotalRefund(smallRefund.add(largeRefund).setScale(2, RoundingMode.HALF_UP));

        repository.save(donation);
        return "redirect:/bottledonation";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<BottleDonation> donation = repository.findById(id);

        if (donation.isPresent()) {
            model.addAttribute("bottleDonation", donation.get());
            return "bottledonation/add";
        }

        return "redirect:/bottledonation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/bottledonation";
    }
}