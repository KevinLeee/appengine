package app.engine.core;

import java.lang.annotation.*;

/**
 * <p>实体或者属性的中文</p>
 * User: MichaelLee
 * Date: 13-1-14
 * Time: 下午2:51
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface ChineseName {
    public String value();
}
