package org.firstwebproject.pp_3_3_1.controllers;

import org.firstwebproject.pp_3_3_1.models.User;
import org.firstwebproject.pp_3_3_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping()
    public String createNewUser (@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String getUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "users/show";
    }

    @GetMapping("/user/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "users/edit";
    }

    @PostMapping("/user/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
