package ua.kiev.prog.controller;

import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;

import java.util.List;

@Controller
public class ContactController extends BaseController {

    @RequestMapping("/")
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        List<Contact> contacts = contactService
                .findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("contacts", contacts);
        model.addAttribute("allPages", getPageCount());

        return "index";
    }

    @RequestMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "contact_add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("contacts", contactService.findByPattern(pattern, null));
        return "index";
    }

    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteContact(@RequestParam(value = "toDeleteContact[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            contactService.deleteContacts(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String phone,
                             @RequestParam String email) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? groupService.findGroup(groupId) : null;

        val contact = Contact.builder()
                .group(group)
                .name(name)
                .surname(surname)
                .phone(phone)
                .email(email)
                .build();
        contactService.addContact(contact);

        return "redirect:/";
    }
}
