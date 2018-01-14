package ua.kiev.prog.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.dao.ContactRepository;
import ua.kiev.prog.model.dao.GroupRepository;
import ua.kiev.prog.model.entity.Group;

/**
 * Created by User on 1/8/2018.
 */
public abstract class BaseService {

    @Autowired
    protected ContactRepository contactRepository;

    @Autowired
    protected GroupRepository groupRepository;

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }
}
