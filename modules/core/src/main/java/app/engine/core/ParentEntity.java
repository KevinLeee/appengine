package app.engine.core;

import java.lang.annotation.*;

/**
 * <p>
 * 如果实体上某个属性的值引用其他实体中的Id，需要在该属性的get方法中加上此注解。
 * 如 ParentEntity(Sample.class) ，其中Sample就是对应实体
 * </p>
 * User: MichaelLee
 * Date: 13-1-15
 * Time: 下午4:41
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface ParentEntity {
    public Class<? extends EntityDeclaration> value();
}
