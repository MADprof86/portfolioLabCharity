package pl.coderslab.charity.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final String CONFIRMATION_MESSAGE = "Darownizna przyjęta do realizacji";
    private final String ERROR_MESSAGE = "Darownizna nie przyjęta z powodu będu: ";
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
        System.out.println("Flash attributes: ");
        return "donation-form";
    }
    @PostMapping("")
    public String saveDonation(@ModelAttribute("donation") @Valid Donation donation,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal User user){

        System.out.println(donation.toString());

            if(result.hasErrors()){

                redirectAttributes.addFlashAttribute("error", ERROR_MESSAGE);
                return "redirect:/donation";
            }
            if(user!=null){
                donation.setUser(user);
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

    //TODO ZRobić PostMaping na kolejnym adresie z listą atrybutów i flashem tylko by je wyświetlić w innym formularzu.
    //TODO spróbować zmienić adres dla Akcji PostMaping()
}
