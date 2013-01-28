package app.engine.core.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * marker interface
 * 标志一个接口是否为myBatis的　mapper
 * 所有的 mapper　都要继承此接口
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SqlMapper {

}
