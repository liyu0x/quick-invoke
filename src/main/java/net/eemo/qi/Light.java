package net.eemo.qi;

/**
 * The annotation describe info that will be invoked and must be defined in {@link LightHouse} class.
 *
 * @author liyu0
 */
public @interface Light {

    /**
     * Request Method
     */
    enum Method {

        GET,

        POST,

        DELETE,

        PUT

    }

    String url();

    Method method() default Method.GET;

}
