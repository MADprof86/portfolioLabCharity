package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;

@Controller
@RequestMapping("/index-admin")
public class AdminController {

    private final DonationService donationService;



    public AdminController(DonationService donationService) {

        this.donationService = donationService;

    }

    @GetMapping()
    public String getAdminForm(@AuthenticationPrincipal User user, Model model){
        Long numberOfDonations = donationService.getDonationsCount();
        Long numberOfBags = donationService.getDonationsCountQuantity();
        Long numberOfDonationsIn30DaysPeriod = donationService.countDonationsFromLast30Days();
        model.addAttribute("numberOfDonationsLast30Days",numberOfDonationsIn30DaysPeriod);
        model.addAttribute("numberOfDonations", numberOfDonations);
        model.addAttribute("numberOfBags",numberOfBags);

        return "index-admin";
    }
}
