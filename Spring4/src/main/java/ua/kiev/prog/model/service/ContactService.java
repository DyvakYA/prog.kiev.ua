package ua.kiev.prog.model.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.entity.Contact;
import ua.kiev.prog.model.entity.Group;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ContactService extends BaseService {

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByPattern(pattern, pageable);
    }

    public void export(List<Contact> contacts) {
        String text = "";
        for (Contact contact : contacts) {
            text = text + contact.toString() + "\n";
        }
        try (FileWriter writer = new FileWriter("D:\\csv.txt", false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            //NOP
        }
    }
}
