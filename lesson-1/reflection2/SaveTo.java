package reflection2;

import java.lang.annotation.*;

/**
 * Created by User on 11/14/2017.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface SaveTo {
    String path();
}
