package pl.coderslab.charity.controler;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.EmailUsedException;
import pl.coderslab.charity.exceptions.PasswordMismatchException;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping()
    public String registerNewUser(@ModelAttribute("user") @Valid User user,
                                  BindingResult result,
                                  @RequestParam("password2") String password2) throws DataNotFoundInDatabaseException {
        if(result.hasErrors()){
            return "register";
        }
        try{
        userService.saveNewUser(user,password2);
        }
        catch (EmailUsedException e){

            result.rejectValue("email", "error.user",e.getMessage());
            return "register";
        }
        catch (PasswordMismatchException e){

            result.rejectValue("password", "error.user",e.getMessage());
            return "register";
        }

        return "login";
    }

}
