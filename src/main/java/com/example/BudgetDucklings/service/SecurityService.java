package com.example.BudgetDucklings.service;

import com.example.BudgetDucklings.model.UserDetails;
import com.example.BudgetDucklings.repository.AuthRep;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SecurityService {
    private AuthRep authRep;
    public SecurityService(AuthRep authRep) {
        this.authRep = authRep;
    }
    public boolean verifyUser(UserDetails userDetails, HttpSession httpSession){
        UserDetails actualUser = authRep.findAllByUsername(userDetails);
        if (actualUser != null && actualUser.getPassword().equals(userDetails.getPassword())){
            httpSession.setAttribute("username", userDetails.getUsername());
            return true;
        }
        else {
            return false;
        }
    }
}
