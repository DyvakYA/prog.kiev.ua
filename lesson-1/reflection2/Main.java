package reflection2;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by User on 11/14/2017.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ContainerSaver containerSaver = new ContainerSaver();
        containerSaver.save();
    }
}

