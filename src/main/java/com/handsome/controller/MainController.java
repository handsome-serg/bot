package com.handsome.controller;

import com.handsome.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    MarketRepository marketRepository;

    @Value("${hostname}")
    private String hostname;

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("rooturl", "http://" + hostname + ":" + port);

        model.addAttribute("marketList", marketRepository.findAll());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return index(model);
    }

}
