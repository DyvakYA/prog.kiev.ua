package ua.kiev.prog.ChatServer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.ChatServer.Sender;
import ua.kiev.prog.ChatServer.entity.User;
import ua.kiev.prog.ChatServer.repository.UserList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by User on 11/25/2017.
 */
public class UserServlet extends HttpServlet {

    private UserList userList = UserList.getInstance();

    private Gson gson;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        User user = Sender.userFromJSON(bufStr);
        if (user != null) {
            if(userList.checkLogin(user)){
                if(userList.checkPassword(user)){
                    resp.setHeader("user","Welcome " + user);
                }else{
                    resp.setHeader("user","User already exist");
                }
            }else{
                userList.add(user);
                resp.setHeader("user","You was successful registered " + user);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(userList.getList());
        System.out.println(json);
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }

    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }

}
