package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
import pl.coderslab.charity.exceptions.PasswordMismatchException;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.RoleService;
import pl.coderslab.charity.service.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/index-admin")
public class AdminController {

    private final DonationService donationService;
    private final RoleService roleService;
    private final UserService userService;
    private final InstitutionService institutionService;



    public AdminController(DonationService donationService, RoleService roleService, UserService userService, InstitutionService institutionService) {

        this.donationService = donationService;

        this.roleService = roleService;
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @GetMapping()
    public String getAdminView(@AuthenticationPrincipal User user, Model model){
        Long numberOfDonations = donationService.getDonationsCount();
        Long numberOfBags = donationService.getDonationsCountQuantity();
        Long numberOfDonationsIn30DaysPeriod = donationService.countDonationsFromLast30Days();
        List<Role> roles = roleService.getAll();


        model.addAttribute("user",user);
        model.addAttribute("numberOfDonationsLast30Days",numberOfDonationsIn30DaysPeriod);
        model.addAttribute("numberOfDonations", numberOfDonations);
        model.addAttribute("numberOfBags",numberOfBags);

        return "index-admin";
    }
    @GetMapping("/admin-profile")
    public String getAdminProfileView(@AuthenticationPrincipal User user, Model model){
        List<Role> roles = roleService.getAll();

        model.addAttribute("roles",roles);
        model.addAttribute("user", user);
        return "admin-profile";
    }
    @PostMapping("/admin-profile")
    public String updateProfile(@ModelAttribute User user, BindingResult result,
                                @RequestParam ("passwordRepeat") String passwordRepeat){
        try {
            userService.updateUser(user,passwordRepeat);
        } catch (PasswordMismatchException e) {
            result.rejectValue("passwordRepeat","password.user","Password do not matach");
            return "admin-profile";
        } catch (EmailUsedException e) {
            throw new RuntimeException(e);
        } catch (DataNotFoundInDatabaseException e) {
            throw new RuntimeException(e);
        }
        return "admin-profile";

    }
    @GetMapping("/admin-institutions")
    public String getInstitutions(@AuthenticationPrincipal User user, Model model){

        model.addAttribute("institutions", institutionService.getAllInstitutions());
        model.addAttribute("donationsByInstitutions",donationService.getDonationsByInstitutionsMap() );

        return "admin-institutions";
    }
}
