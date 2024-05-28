package com.beauty.config.imlog;


import java.lang.annotation.*;

/**
 * 酒管重要日志记录
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImLog {

    /**
     * 日志类型
     * @return
     */
    String type();

    /**
     * 描述
      * @return
     */
    String mark();


}
