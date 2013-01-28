package app.engine.core;

import java.lang.annotation.*;

/**
 * 在实体的get属性方法上加。用来表示当前实体或者属性的中文意思
 */


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface Property {
    public static final String SHORT_DATE = "yyyy-MM-dd";
    public static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";

    public String chineseName();

    public int maxLength() default 0;

    public boolean required() default false;

    public String pattern() default "";
}
