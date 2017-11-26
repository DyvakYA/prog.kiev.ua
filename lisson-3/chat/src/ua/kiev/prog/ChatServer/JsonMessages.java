package ua.kiev.prog.ChatServer;

import ua.kiev.prog.ChatServer.entity.Message;
import ua.kiev.prog.ChatServer.repository.MessageList;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, String userTo, String userFrom, int fromIndex) {

        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            fromIndex++;
            if ((sourceList.get(i).getTo().equals(userTo))
                    || (sourceList.get(i).getTo().equals(""))
                    || (sourceList.get(i).getFrom().equals(userFrom))) {
                list.add(sourceList.get(i));
            }
        }
        System.out.println(fromIndex);
        MessageList.getInstance().setN(fromIndex);
    }
}

