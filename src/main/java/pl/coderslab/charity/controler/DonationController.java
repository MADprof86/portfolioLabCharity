package pl.coderslab.charity.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private static final String CONFIRMATION_MESSAGE = "Darownizna przyjęta do realizacji";
    private static final String ERROR_MESSAGE = "Darownizna nie przyjęta z powodu będu: ";
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private DonationService donationService;
    @GetMapping()
    public String getDonationForm(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("donation", new Donation());
        model.addAttribute("institutions",institutions);
        model.addAttribute("categories",categories);
        return "donation-form";
    }
    @PostMapping("/form")
    public String saveDonation(@ModelAttribute("donation") @Valid Donation donation,
                               BindingResult result, Model model){

        System.out.println(donation.toString());

            if(result.hasErrors()){
                return "donation-form";
            }
            try{
                donationService.save(donation);
                model.addAttribute("success", CONFIRMATION_MESSAGE);
                model.addAttribute("donation", new Donation());
            }
            catch (Exception e){
                model.addAttribute("error", ERROR_MESSAGE + e.getMessage());
            }
            return "donation-form";
    }
}
