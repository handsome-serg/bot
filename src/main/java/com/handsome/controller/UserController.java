package com.handsome.controller;

import com.handsome.repository.User;
import com.handsome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public String addNewUser (@RequestParam(required = false, defaultValue = "") String name,
                                            @RequestParam(required = false, defaultValue = "") String email,
                                            Model model){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        model.addAttribute("result", "Saved " + name + " " + email);

        return "adduser";
    }

    @GetMapping(path="/add")
    public String addNewUserForm(Model model){
        model.addAttribute("result", "");
        return "adduser";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("filter")
    public String filterUser (@RequestParam String filter, Model model){
        Iterable<User> users;

        if(filter != null && !filter.isEmpty()){
            users = userRepository.findByName(filter);
        } else {
            users = userRepository.findAll(); //find all if no filter
        }

        model.addAttribute("filter", filter);
        model.addAttribute("users", users);
        return "filteruser";
    }

}
