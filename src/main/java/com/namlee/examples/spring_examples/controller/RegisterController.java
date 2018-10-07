package com.namlee.examples.spring_examples.controller;

import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.namlee.examples.spring_examples.domain.Role;
import com.namlee.examples.spring_examples.domain.User;
import com.namlee.examples.spring_examples.repository.RoleRepository;
import com.namlee.examples.spring_examples.service.UserService;
import com.namlee.examples.spring_examples.validator.RegisterValidator;

@Controller
public class RegisterController {

    @Autowired
    private RegisterValidator registerValidator;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String getRegister(Model model) {

        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid User user, BindingResult result, RedirectAttributes redirect) {

        registerValidator.validate(user, result);
        if (result.hasErrors()) {
            return "admin/register";
        }

        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_MEMBER"));
        user.setRoles(roles);

        userService.save(user);

        redirect.addFlashAttribute("success", "Registered successfully!");
        return "redirect:/";
    }

}
