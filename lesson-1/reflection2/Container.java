package reflection2;

import java.io.IOException;

/**
 * Created by User on 11/14/2017.
 */
@SaveTo(path = "c:\\file.txt")
public class Container {
    String text = "qweqweqwe";

    @Saver
    public void save(String path) throws IOException {

        System.out.println("OK!" + " " + path + " " + text);

        //        FileWriter fileWriter = new FileWriter(path);
//        try {
//            fileWriter.write(text);
//        }finally {
//            fileWriter.close();
//        }
    }
}
