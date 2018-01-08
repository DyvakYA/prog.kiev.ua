package ua.kiev.prog.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.model.entity.Group;

import java.util.List;

/**
 * Created by User on 1/8/2018.
 */
@Service
public class GroupService extends BaseService{

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteGroup(long[] toDelete) {
        for(long id: toDelete)
            groupRepository.delete(id);
    }

    @Transactional(readOnly=true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly=true)
    public Group findGroup(long id) {
        return groupRepository.findOne(id);
    }
}
