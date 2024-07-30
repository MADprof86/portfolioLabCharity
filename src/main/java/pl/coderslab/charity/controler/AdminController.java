package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.model.User;

@Controller
@RequestMapping("/index-admin")
public class AdminController {
    @GetMapping()
    public String getAdminForm(@AuthenticationPrincipal User user, Model model){
            return "index-admin";
    }
}
