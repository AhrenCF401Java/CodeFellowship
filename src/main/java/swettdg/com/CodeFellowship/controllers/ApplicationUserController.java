package swettdg.com.CodeFellowship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import swettdg.com.CodeFellowship.models.ApplicationUser;
import swettdg.com.CodeFellowship.models.ApplicationUserRepository;
import swettdg.com.CodeFellowship.models.Post;
import swettdg.com.CodeFellowship.models.PostRepository;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired ApplicationUserRepository applicationUserRepository;
    @Autowired PostRepository postRepository;

    @PostMapping("/signup")
    public RedirectView createNewUser(String username, String password, String firstName, String lastName, String dob, String bio){
//        create user and salt/hash password
        ApplicationUser u = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dob, bio);
//        save the user info in DB
        applicationUserRepository.save(u);
//        send user back to the homepage
        // maybe autologin?
        Authentication authentication = new UsernamePasswordAuthenticationToken(u, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/userdetails");
    }

    @GetMapping("/userdetails")
    public String displayUserDetails(Principal p, Model m){
//        get the user info from the repo
        ApplicationUser curUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("user", curUser);
        return "userdetails";
    }

    @PostMapping("/userdetails")
    public RedirectView submitPosts(String body, Principal p){
//        get the current user p could be null if session expired
        ApplicationUser curUser = applicationUserRepository.findByUsername(p.getName());
//        Create the timestamp
        String timeCreated= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//        Save all the things
        Post post = new Post(body,timeCreated,curUser);
        postRepository.save(post);
        return new RedirectView("/userdetails");
    }


//
    @GetMapping("/userdetails/{id}")
    public String getUserDetailsPage(@PathVariable Long id, Principal p, Model m){
        m.addAttribute("name", p);
        return "userdetails" + id;
    }

    @GetMapping("/signup")
    public String getSignUpPage(){ return "signup";};

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }



}
