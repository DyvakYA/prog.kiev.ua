package ua.kiev.prog.ChatServer.repository;

import ua.kiev.prog.ChatServer.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/25/2017.
 */
public class UserList {

    private static final UserList usrList = new UserList();

    private final List<User> list = new ArrayList<>();

    public static UserList getInstance() {
        return usrList;
    }

    private UserList() {

    }

    public synchronized void add(User user) {
        list.add(user);
    }

    public boolean checkLogin(User user) {
        if (list.stream().anyMatch((s) -> s.getLogin().equals(user.getLogin()))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPassword(User user) {
        if(list.stream().anyMatch((s) -> s.getPassword().equals(user.getPassword()))){
            return true;
        }else{
            return false;
        }
    }

    public List<User> getList() {
        return list;
    }
}
