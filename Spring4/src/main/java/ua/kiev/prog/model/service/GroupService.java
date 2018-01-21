package ua.kiev.prog.model.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 1/8/2018.
 */
@Service
public class GroupService extends BaseService {

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteGroup(long[] toDelete) {
        for (long id : toDelete)
            groupRepository.delete(id);
    }

    @Transactional
    public void deleteGroupWithContacts(long[] toDelete) {
        for (long id : toDelete) {
            Group group = groupRepository.findOne(id);
            groupRepository.delete(id);
            List<Contact> contacts = group.getContacts();
            for (Contact contact : contacts)
                contactRepository.delete(contact);
        }
    }

    @Transactional(readOnly = true)
    public Map<Group, Long> findAll(Pageable pageable) {
        List<Group> groups = groupRepository.findAll(pageable).getContent();
        Map<Group, Long> countByGroup = new HashMap<>();
        for (Group group : groups) {
            countByGroup.put(group, Long.valueOf(group.getContacts().size()));
        }
        return countByGroup;
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupRepository.findAll();
    }
}
