package pl.coderslab.charity.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Controller

public class HomeController {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private DonationRepository donationRepository;
    @GetMapping("/")
    public String getHomePage(Model model){
        List<Donation> donations = donationRepository.findAll();
        int donationCount = donations.size();
        int bagsCount = donations.stream().mapToInt(Donation::getQuantity).sum();

        model.addAttribute("donationCount",donationCount);
        model.addAttribute("bagsCount",bagsCount);

        List<Institution> institutions = institutionRepository.findAll();


        model.addAttribute("insitutions",institutions);
        return "index";
    }
}
