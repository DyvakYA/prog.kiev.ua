package ua.kiev.prog.ChatClient;

import ua.kiev.prog.ChatClient.entity.Message;
import ua.kiev.prog.ChatClient.entity.User;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            User user = registration(scanner);
            Thread th = new Thread(new GetThread(user));
            th.setDaemon(true);
            th.start();
            System.out.println("Enter your message: ");
            while (true) {
                Message message;
                String text = scanner.nextLine();
                if (text.isEmpty()) break;
                if (text.equals("users")) {
                    Sender.getInstance().get(Utils.getURL() + "/login");
                } else {
                    message = messageBuilder(user, text);
                    int res = Sender.getInstance().send(Utils.getURL() + "/add", message);
                    if (res != 200) { // 200 OK
                        System.out.println("HTTP error occurred: " + res);
                    }
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static Message messageBuilder(User user, String text) {
        String[] textParts;
        Message message;
        if (text.toLowerCase().contains(":".toLowerCase())) {
            textParts = text.split(":");
            message = new Message.Builder()
                    .setFrom(user.getLogin())
                    .setTo(textParts[0])
                    .setText(textParts[1])
                    .build();
        } else {
            message = new Message.Builder()
                    .setFrom(user.getLogin())
                    .setTo("")
                    .setText(text)
                    .build();
        }
        return message;
    }

    private static User registration(Scanner scanner) throws IOException {

        String header = Sender.getInstance().getHeaderCurrentUser();
        User user = null;
        while (header == null || header.equals("User already exist")) {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();
            System.out.println("Enter your password: ");
            String password = scanner.nextLine();
            user = new User.Builder()
                    .setLogin(login)
                    .setPassword(password)
                    .build();
            int result = Sender.getInstance().send(Utils.getURL() + "/login", user);
            header = Sender.getInstance().getHeaderCurrentUser();
            System.out.println(header);
        }
        return user;
    }
}
