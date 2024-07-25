package pl.coderslab.charity.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
import pl.coderslab.charity.exceptions.PasswordMismatchException;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private DonationService donationService;

    @GetMapping
    public String showProfile(@AuthenticationPrincipal User user,
                              Model model) {

        List<Donation> donations = donationService.findAllByUser(user);
        int donationsCount = donationService.countByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("donationsCount", donationsCount);
        model.addAttribute("donations",donations);
        return "profile";
    }

    @PostMapping
    public String updateProfile(@ModelAttribute("user") @Valid User user,
                                BindingResult result,
                                @RequestParam("password2") String password2
    ) {

        if (result.hasErrors()) {
            return "profile";
        }
        try {

            userService.updateUser(user, password2);
        } catch (DataNotFoundInDatabaseException e) {
            result.rejectValue("userId", "userId.user", "User not found");
            return "profile";
        } catch (PasswordMismatchException e) {
            result.rejectValue("password2", "password.user", "Password Mismatch");
            return "profile";
        } catch (EmailUsedException e) {
            result.rejectValue("email", "email.user", "Email is beeing used");
            return "profile";
        }
        return "profile";

    }
}
