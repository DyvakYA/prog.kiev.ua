package reflection3;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 11/14/2017.
 */
class SimpleSerialization {

    SimpleClass simpleClass1 = new SimpleClass(1, 2, 3, "qwertyuio");
    SimpleClass simpleClass2 = new SimpleClass();
    Map<String, Object> map = new HashMap<String, Object>();

    Class<?> clazz = SimpleClass.class;
    Field[] fields = clazz.getDeclaredFields();

    public void serialize() throws IllegalAccessException {

        for (Field field : fields){
            serializeAnnotatedFields(simpleClass1, map, field);
        }
        System.out.println(simpleClass1);

        for (Field field : fields){
            deserializeAnnotatedFields(field);
        }
        System.out.println(simpleClass2);

    }

    private void serializeAnnotatedFields(SimpleClass simpleClass, Map<String, Object> map, Field field) throws IllegalAccessException {
        if (field.isAnnotationPresent(Save.class)) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(simpleClass));
            System.out.println(field.get(simpleClass));

        }
    }

    private void deserializeAnnotatedFields(Field field) throws IllegalAccessException {

        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey().equals(field.getName())) {
                field.setAccessible(true);
                System.out.println(entry.getValue());
                field.set(simpleClass2, entry.getValue());
            }
        }
    }
}
