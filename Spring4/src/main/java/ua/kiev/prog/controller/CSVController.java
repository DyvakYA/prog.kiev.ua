package ua.kiev.prog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kiev.prog.model.entity.Contact;

import java.util.List;

/**
 * Created by User on 1/8/2018.
 */
@Controller
public class CSVController extends BaseController {

    @RequestMapping("/export")
    public String export(){
        List<Contact> contacts = contactService.findAll();
        contactService.export(contacts);
        return "redirect:/";
    }
}
