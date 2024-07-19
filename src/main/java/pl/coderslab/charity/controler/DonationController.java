package pl.coderslab.charity.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping()
    public String getDonationForm(Model model){
        List<Institution> institutions = institutionRepository.findAll();
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("institutions",institutions);
        model.addAttribute("categories",categories);
        return "donation-form";
    }
}
