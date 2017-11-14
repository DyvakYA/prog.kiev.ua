package reflection1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by User on 11/14/2017.
 */
public class Refl {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Class<?> ref = Tester.class;
        Method[] methods = ref.getDeclaredMethods();
        for (Method method : methods) {
            int result = invokerForAnnotatedMethods(method);
            System.out.println(result);
        }
    }

    private static int invokerForAnnotatedMethods(Method method) throws IllegalAccessException, InvocationTargetException {
        int result = 0;
        if (method.isAnnotationPresent(Test.class)) {
            Test annotation = method.getAnnotation(Test.class);
            result = (Integer) method.invoke(null, annotation.a(), annotation.b());
        }
        return result;
    }
}

