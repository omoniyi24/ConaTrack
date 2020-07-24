package com.jayjav.coronavirustracker.controllers;

import com.jayjav.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoronaVirusDataController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

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

}
