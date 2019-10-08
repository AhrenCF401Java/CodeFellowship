package swettdg.com.CodeFellowship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import swettdg.com.CodeFellowship.models.ApplicationUser;
import swettdg.com.CodeFellowship.models.ApplicationUserRepository;

@Controller
public class ApplicationUserController {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired ApplicationUserRepository applicationUserRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password){
//        create user and salt/hash password
        ApplicationUser u = new ApplicationUser(username,passwordEncoder.encode(password));
//        save the user info in DB
        applicationUserRepository.save(u);
//        send user back to the homepage
        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }



}
