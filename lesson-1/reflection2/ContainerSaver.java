package reflection2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by User on 11/14/2017.
 */
class ContainerSaver {

    public void save() throws InvocationTargetException, IllegalAccessException {

        Container container = new Container();
        Class<?> containerClass = Container.class;
        if (containerClass.isAnnotationPresent(SaveTo.class)) {
            SaveTo annotation = containerClass.getAnnotation(SaveTo.class);
            String s = annotation.path();
            Method[] methods = containerClass.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    method.invoke(container, s);
                }
            }
        }
    }
}
