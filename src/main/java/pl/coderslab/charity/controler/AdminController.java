package pl.coderslab.charity.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/index-admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private DonationService donationService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping()
    public String getAdminForm(@AuthenticationPrincipal User user, Model model){
        List<User> userList = userService.getAllUsers();
        List<Donation> donationList = donationService.getAllDonations();
        List<Institution> institutionList = institutionService.getAllInstitutions();
        List<Category> categoryList = categoryService.getAllCategories();
        Long numberOfBags = donationList.stream().mapToLong(Donation::getQuantity).sum();
        model.addAttribute("users",userList);
        model.addAttribute("donations",donationList);
        model.addAttribute("institutions",institutionList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("numberOfBags",numberOfBags);
        return "index-admin";
    }
}
