package ua.kiev.prog.controller;

import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;

import java.util.List;
import java.util.Map;

/**
 * Created by User on 1/8/2018.
 */
@Controller
public class GroupController extends BaseController {

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(
            @PathVariable(value = "id") long groupId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? groupService.findGroup(groupId) : null;
        if (page < 0) page = 0;

        List<Contact> contacts = contactService
                .findByGroup(group, new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("contacts", contacts);
        model.addAttribute("byGroupPages", getPageCount(group));
        model.addAttribute("groupId", groupId);

        return "index";
    }

    @RequestMapping(value = "/group/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteGroup(@RequestParam(value = "toDeleteGroup[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            groupService.deleteGroup(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/group/deleteGroupAndContacts", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteGroupWithContacts(@RequestParam(value = "toDeleteGroup[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            groupService.deleteGroupWithContacts(toDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/groups")
    public String groups(Model model, @RequestParam(required = false, defaultValue = "0") Integer page) {
        if (page < 0) page = 0;

        Map<Group, Long> groups = groupService
                .findAll(new PageRequest(page, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));

        model.addAttribute("groups", groups);
        model.addAttribute("allPages", getPageCount());
        return "groups_page";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(@RequestParam String group_name,
                           @RequestParam(value = "name", required = false) String[] names,
                           @RequestParam(value = "surname", required = false) String[] surnames,
                           @RequestParam(value = "phone", required = false) String[] phones,
                           @RequestParam(value = "email", required = false) String[] emails) {

        val group = Group.builder()
                .name(group_name)
                .build();
        groupService.addGroup(group);
        for (int i = 0; i < names.length; i++) {
            if (!names[i].isEmpty()) {
                val contact = Contact.builder()
                        .group(group)
                        .name(names[i])
                        .surname(surnames[i])
                        .phone(phones[i])
                        .email(emails[i])
                        .build();
                contactService.addContact(contact);
            }
        }
        return "redirect:/";
    }
}
