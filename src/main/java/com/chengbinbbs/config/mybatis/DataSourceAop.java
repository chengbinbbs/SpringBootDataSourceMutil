package com.chengbinbbs.config.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author zhangcb
 * @created 2017-07-10 17:34.
 */
@Aspect
@Component
public class DataSourceAop {

    private static Log logger = LogFactory.getLog(DataSourceAop.class);

    @Before("execution(* com.chengbinbbs.mapper..*.find*(..)) || execution(* com.chengbinbbs.mapper..*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        logger.info("dataSource切换到：Read");
    }

    @Before("execution(* com.chengbinbbs.mapper..*.insert*(..)) || execution(* com.chengbinbbs.mapper..*.insert*(..)) || execution(* com.chengbinbbs.mapper..*.update*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        logger.info("dataSource切换到：write");
    }

}
