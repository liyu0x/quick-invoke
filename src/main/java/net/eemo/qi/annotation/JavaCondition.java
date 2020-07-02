package net.eemo.qi.annotation;

/**
 * @author liyu0
 */
public @interface JavaCondition {

    enum VERSION {
        /**
         * java 1.8 or less
         */
        EIGHT_OR_LESS,

        ELEVEN;
    }

    VERSION version();

}
