package com.jayjav.coronavirustracker.controllers;

import com.jayjav.coronavirustracker.dto.EmailRequest;
import com.jayjav.coronavirustracker.enums.APIResponseCode;
import com.jayjav.coronavirustracker.enums.Status;
import com.jayjav.coronavirustracker.services.CoronaVirusDataService;
import com.jayjav.coronavirustracker.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CoronaVirusDataController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/displayCases")
    public String home(Model model){
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases", coronaVirusDataService.getTotalReportedCases());
        model.addAttribute("newTotalReportedCases", coronaVirusDataService.getTotalNewCases());
        return "display-cases";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        return "contact";
    }

    @GetMapping("/features")
    public String features(Model model){
        return "features";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        return "blog";
    }

    @RequestMapping("/email")
    public String sendMail(@RequestParam(value = "email", required = true) String email,
                           Model model) {
        String reportformat = "pdf";
        if(!email.isEmpty()){
            emailService.sendReportToEmail(reportformat,email);
        }
        model.addAttribute("email", "Email");
        model.addAttribute("msg",  "Email Sent to " + email + "Successfully");
        return "display-cases";
    }

}
