package ua.kiev.prog.ChatServer.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.ChatServer.JsonMessages;
import ua.kiev.prog.ChatServer.entity.Message;

import java.util.LinkedList;
import java.util.List;

public class MessageList {

    private int n = 0;
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<Message>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(String userFor, String userFrom, int n) {
        if (n == list.size()) return null;
        
        return gson.toJson(new JsonMessages(list, userFor, userFrom, n));
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
}
