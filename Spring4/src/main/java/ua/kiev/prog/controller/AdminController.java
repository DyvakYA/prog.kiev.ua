package ua.kiev.prog.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.CustomUser;

import java.util.List;

/**
 * Created by User on 1/21/2018.
 */
@Controller
public class AdminController extends BaseController {

    @RequestMapping("/admin/contacts")
    public String contacts(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;
        List<Contact> contacts = contactService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("contacts", contacts);
        model.addAttribute("allPages", getPageCount());
        model.addAttribute("contacts_link", "/admin/contacts");
        model.addAttribute("users_link", "/admin/users");
        return "contacts";
    }

    @RequestMapping("/admin/users")
    public String users(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (page < 0) page = 0;
        List<CustomUser> users = userService.findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("users", users);
        model.addAttribute("allPages", getPageCount());
        model.addAttribute("contacts_link", "/admin/contacts");
        model.addAttribute("users_link", "/admin/users");
        return "users";

    }
}
