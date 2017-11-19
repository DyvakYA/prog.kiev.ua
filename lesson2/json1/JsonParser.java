package lesson2.json1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * Created by User on 11/19/2017.
 */
public class JsonParser {

    public static final String PATH = "src/lesson2/json1/1";

    void parse() throws IOException {
        Gson gson = new GsonBuilder().create();
        User obj = gson.fromJson(new FileReader(PATH), User.class);
        System.out.println(obj);
    }
}
