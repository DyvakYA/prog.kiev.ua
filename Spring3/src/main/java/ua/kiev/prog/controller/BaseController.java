package ua.kiev.prog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.prog.model.entity.Group;
import ua.kiev.prog.model.service.ContactService;
import ua.kiev.prog.model.service.GroupService;

/**
 * Created by User on 1/8/2018.
 */
public abstract class BaseController {

    static final int DEFAULT_GROUP_ID = -1;
    static final int ITEMS_PER_PAGE = 6;

    @Autowired
    protected ContactService contactService;

    @Autowired
    protected GroupService groupService;

    protected long getPageCount() {
        long totalCount = contactService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    protected long getPageCount(Group group) {
        long totalCount = contactService.countByGroup(group);
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

}
