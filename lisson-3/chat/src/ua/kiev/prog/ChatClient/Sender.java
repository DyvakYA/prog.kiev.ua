package ua.kiev.prog.ChatClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.ChatClient.entity.Message;
import ua.kiev.prog.ChatClient.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by User on 11/25/2017.
 */
public class Sender {

    private String header;

    public static final Sender instance = new Sender();

    public static Sender getInstance() {
        return instance;
    }

    public int send(String url, Object object) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        try {
            String json = toJSON(object);
            os.write(json.getBytes(StandardCharsets.UTF_8));
            header = conn.getHeaderField("user");
            return conn.getResponseCode();
        } finally {
            os.close();
        }
    }

    public List<User> get(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        InputStream is = conn.getInputStream();
        List<User> users;
        try {
            byte[] buf = requestBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            users = new Gson().fromJson(strBuf, List.class);
            if (users != null) {
                System.out.println(users);
            }
        } finally {
            is.close();
        }
        return users;
    }

    public String toJSON(Object object) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    public static Message fromJSONMessage(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }

    public static User fromJSONUser(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    private byte[] requestBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

    public String getHeaderCurrentUser() {
        return header;
    }
}
