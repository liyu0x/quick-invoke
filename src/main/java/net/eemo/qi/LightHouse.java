package net.eemo.qi;

import java.lang.annotation.*;

/**
 * The annotation is used to remark a Class as Remoter-Invoke entry class.
 *
 * @author liyu0
 */
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface LightHouse {

    String host() default "";

}
