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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        System.out.println("Flash attributes: " + model.asMap());
        return "donation-form";
    }
    @PostMapping("/form")
    public String saveDonation(@ModelAttribute("donation") @Valid Donation donation,
                               BindingResult result, RedirectAttributes redirectAttributes, Model model){

        System.out.println(donation.toString());

            if(result.hasErrors()){

                redirectAttributes.addFlashAttribute("error", ERROR_MESSAGE);
                return "redirect:/donation";
            }
            try{
                Donation newDonation = donationService.save(donation);
                redirectAttributes.addFlashAttribute("donationSuccessfullyAdded",newDonation);
                redirectAttributes.addFlashAttribute("success", CONFIRMATION_MESSAGE);
                return "redirect:/donation";
            }
            catch (Exception e){
                redirectAttributes.addFlashAttribute("error", ERROR_MESSAGE + e.getMessage());
                return "redirect:/donation";
            }

    }
}
