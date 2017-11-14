package reflection3;

import java.lang.annotation.*;

/**
 * Created by User on 11/14/2017.
 */
@Inherited
@Retention(value= RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Save {
}
