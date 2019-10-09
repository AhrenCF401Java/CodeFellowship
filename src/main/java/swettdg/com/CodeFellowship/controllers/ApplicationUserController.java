package swettdg.com.CodeFellowship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import swettdg.com.CodeFellowship.models.ApplicationUser;
import swettdg.com.CodeFellowship.models.ApplicationUserRepository;

import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired ApplicationUserRepository applicationUserRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password, String firstName, String lastName, String dob, String bio){
//        create user and salt/hash password
        ApplicationUser u = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dob, bio);
//        save the user info in DB
        applicationUserRepository.save(u);
//        send user back to the homepage
        return new RedirectView("/");
    }

    @GetMapping("/userdetails/{username}")
    public String getUserDetailsPage(@PathVariable Long id, Principal p, Model m){
        m.addAttribute("name", p);
        return "userdetails" + username;}

    @GetMapping("/signup")
    public String getSignUpPage(){ return "signup";};

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }



}
