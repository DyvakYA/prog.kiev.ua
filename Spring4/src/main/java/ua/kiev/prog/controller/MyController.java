package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.prog.controller.validator.UserFormValidator;
import ua.kiev.prog.model.entity.CustomUser;
import ua.kiev.prog.model.entity.UserRole;
import ua.kiev.prog.model.service.UserService;

import javax.validation.Valid;

@Controller

public class MyController {

    @Autowired
    private UserService userService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        CustomUser dbUser = userService.findByLogin(login);
        model.addAttribute("login", login);
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("email", dbUser.getEmail());
        model.addAttribute("phone", dbUser.getPhone());
        if (dbUser.getRole().equals(UserRole.ADMIN)) {
            model.addAttribute("contacts_link", "/admin/contacts");
            model.addAttribute("users_link", "/admin/users");
            return "admin";
        }
        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @RequestParam(required = false) String email,
                         @Valid @RequestParam(required = false) String phone) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        userService.updateUser(login, email, phone);

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") @Valid CustomUser user, BindingResult result, Model model) {
        //run Spring validator manually
        new UserFormValidator().validate(user, result);

        model.addAttribute("user", user);

        String passHash = passwordEncoder.encodePassword(user.getPassword(), null);

        if (result.hasErrors()) {
            return "register";

        } else if (!userService.addUser(user.getLogin(), passHash, UserRole.USER, user.getEmail(), user.getPhone())) {
            model.addAttribute("exists", true);
            model.addAttribute("login", user.getLogin());
            model.addAttribute("user", user);
            return "register";
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "user", new CustomUser());
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
}
