package pl.coderslab.charity.controler;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.exceptions.DataNotFoundInDatabaseException;
import pl.coderslab.charity.exceptions.PasswordMismatchException;
import pl.coderslab.charity.model.User;

import pl.coderslab.charity.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping()
    public String getLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login";
    }


}
