package com.diu.coop.controller;

import com.diu.coop.model.*;
import com.diu.coop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Scope(scopeName = "prototype")
public class ApplicationController {
    @Autowired
    private Users user;
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private Roles role;
    @Autowired
    private DepositJPARepository depositRepo;
    @Autowired
    private Deposit deposit;
    @Autowired
    private DepositOptions depositOptions;
    @Autowired
    private DepositOptionsJpaRepository depositOptionRepo;
    @Autowired
    private DepositTransactionJpaRepository depositTransactionRepo;
    @Autowired
    private Loan loan;
    @Autowired
    private LoanJPARepository loanRepo;
    @Autowired
    private LoanOption loanOptions;
    @Autowired
    private LoanOptionJpaRepository loanOptionRepo;
    @Autowired
    private LoanTransactionJpaRepository loanTransactionJpaRepo;

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

    @PostMapping(value = "updateUser",consumes="application/json")
    @ResponseBody
    public String updateUser(@RequestBody Users user) {
        String status = null;
        try {
            userRepo.save(user);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @GetMapping("getUser")
    @ResponseBody
    public Users getUser(@RequestParam("mobileNumber") String number){
        return userRepo.findByMobileNumber(number);
    }


    @PostMapping(value = "createDiposit")
    @ResponseBody
    public String saveDeposit(@RequestParam("depositOptionId") int id, @RequestParam("userId") int userId) {
        String status = null;
        try {
            depositOptions = depositOptionRepo.findById(id);
            user = userRepo.findByUserId(id);
            deposit.setTotalMonths(depositOptions.getMonths());
            deposit.setTargetAmount(depositOptions.getAmount());
            deposit.setTotalAmount((depositOptions.getAmount() * depositOptions.getPercentage())/100);
            deposit.setLastInstallmentDate(LocalDateTime.now().plusMonths(depositOptions.getMonths()));
            deposit.setUser(user);
            depositRepo.save(deposit);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @PostMapping(value = "makeDipositTransaction",consumes="application/json")
    @ResponseBody
    public String makeDepositTransaction(@RequestBody DepositTransaction depositTransaction) {
        String status = null;
        try {
            depositTransaction.setTime(LocalDateTime.now());
            depositTransactionRepo.save(depositTransaction);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @GetMapping("getUserDeposit")
    @ResponseBody
    public Deposit getDeposit(@RequestParam("depositId") int id){
        return depositRepo.findById(id);
    }

    @GetMapping("getUserDeposits")
    @ResponseBody
    public List<Deposit> getDeposits(@RequestParam("userId") int id){
        return depositRepo.findByUserUserId(id);
    }

    @PostMapping(value = "takeLoan")
    @ResponseBody
    public String takeLoan(@RequestParam("loanOptionId") int id, @RequestParam("userId") int userId) {
        String status = null;
        try {
            loanOptions = loanOptionRepo.findById(id);
            user = userRepo.findByUserId(id);
            loan.setTotalMonths(loanOptions.getMonths());
            loan.setTargetAmount(loanOptions.getAmount());
            loan.setTotalAmount((loanOptions.getAmount() * loanOptions.getPercentage())/100);
            loan.setLastInstallmentDate(LocalDateTime.now().plusMonths(loanOptions.getMonths()));
            loan.setUser(user);
            loanRepo.save(loan);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @PostMapping(value = "makeLoanTransaction",consumes="application/json")
    @ResponseBody
    public String makeLoanTransaction(@RequestBody LoanTransaction loanTransaction) {
        String status = null;
        try {
            loanTransaction.setTime(LocalDateTime.now());
            loanTransactionJpaRepo.save(loanTransaction);
            status = "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            status = "failed";
        }
        return status;
    }

    @GetMapping("getUserLoan")
    @ResponseBody
    public Loan getLoan(@RequestParam("loanId") int id){
        return loanRepo.findById(id);
    }

    @GetMapping("getUserLoans")
    @ResponseBody
    public List<Loan> getLoans(@RequestParam("userId") int id){
        return loanRepo.findByUserUserId(id);
    }

    @PostMapping("logoutUser")
    @ResponseBody
    public Principal logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return request.getUserPrincipal();
    }

    @GetMapping("adminGetsUsers")
    @ResponseBody
    public List<Users> adminGetsUsers(){
        return userRepo.findAll();
    }

    @GetMapping("adminGetsDeposits")
    @ResponseBody
    public List<Deposit> adminGetsDeposits(){
        return depositRepo.findAll();
    }

}
