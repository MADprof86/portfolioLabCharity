package pl.coderslab.charity.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String getRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping()
    public String registerNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) throws DataNotFoundInDatabaseException {
        try{
        userService.saveNewUser(user);
        }
        catch (EmailUsedException e){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/register";
        }

        return "/register";
    }

}
