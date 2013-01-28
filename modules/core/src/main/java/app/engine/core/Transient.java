package app.engine.core;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * <code>Transient.java</code>
 * <p>功能:实现hibernate-jpa包中的不持久化相应的get方法
 * <p/>
 * <p>Copyright 嗨学网 2012 All right reserved.
 *
 * @author 文齐辉 wenqihui@sunland.org.cn 时间 2012-11-19 下午4:47:23
 * @version 1.0
 *          </br>最后修改人 无
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD})
public @interface Transient {

}
