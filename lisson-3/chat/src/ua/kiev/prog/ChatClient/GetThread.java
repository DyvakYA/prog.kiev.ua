package ua.kiev.prog.ChatClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ua.kiev.prog.ChatClient.entity.Message;
import ua.kiev.prog.ChatClient.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetThread implements Runnable {
    private final Gson gson;
    private int n;
    private User user;

    public GetThread(User user) {
        gson = new GsonBuilder().create();
        this.user = user;
    }

    @Override
    public void run() {
        try {
            while ( ! Thread.interrupted()) {
                URL url = new URL(Utils.getURL()
                        + "/get?"
                        + "to=" + user.getLogin()
                        + "&from=" + user.getLogin()
                        +"&messageFrom=" + n);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                String header2 = http.getHeaderField("n");
                if (header2 != null) {
                    n = Integer.parseInt(header2);
                }
                InputStream is = http.getInputStream();
                try {
                    byte[] buf = requestBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);
                    if (list != null) {
                        for (Message m : list.getList()) {
                            System.out.println(m);
                            //n++;
                        }
                    }
                } finally {
                    is.close();
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
}
