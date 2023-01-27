package com.example.BudgetDucklings.controller;

import com.example.BudgetDucklings.model.UserDetails;
import com.example.BudgetDucklings.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class UserSessionController {
    private SecurityService securityService;
    public UserSessionController(SecurityService securityService){
        this.securityService = securityService;
    }
    @GetMapping
    public String logIn (HttpSession session, @ModelAttribute("user") UserDetails userDetails){
        String username = (String)session.getAttribute("username");

        if (username == null){
            return "Home";
        } else {
            return "redirect:/invoice";
        }
    }
    @PostMapping("/verify")
    public String verify (HttpSession session, @ModelAttribute("user") UserDetails userDetails) {
        boolean correct = securityService.verifyUser(userDetails, session);
        if (correct){
            return "redirect:/invoice";
        } else {
            return "redirect:/home";
        }
    }
    @PostMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/home";
    }
}
