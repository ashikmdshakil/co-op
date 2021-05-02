package com.diu.coop.controller;

import com.diu.coop.model.Roles;
import com.diu.coop.model.Users;
import com.diu.coop.repositories.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class ApplicationController {
    @Autowired
    private Users user;
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private Roles role;

    @GetMapping("/")
    public String getHomePage(){
        return "index.html";
    }

    @GetMapping(value = "login")
    @ResponseBody
    public Principal login(Principal userPrincipal, @RequestParam("number") String number, HttpServletRequest request ) {
        user = userRepo.findByMobileNumber(number);
        user.setLastLoginDate(user.getLoginDate());
        user.setLoginDate(LocalDateTime.now());
        user.setLastLoginIp(user.getLoginIp());
        user.setLoginIp(request.getRemoteAddr());
        userRepo.save(user);
        return userPrincipal;
    }

    @PostMapping(value = "signup",consumes="application/json")
    @ResponseBody
    public String signupUser(@RequestBody Users user, HttpServletRequest request) {
        String status = null;
        try {
            user.setCreateDate(LocalDateTime.now());
            role.setRoleId(1);
            user.setRoles(role);
            role.getUsers().add(user);
            userRepo.save(user);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @PostMapping("logoutUser")
    @ResponseBody
    public Principal logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return request.getUserPrincipal();
    }

}
