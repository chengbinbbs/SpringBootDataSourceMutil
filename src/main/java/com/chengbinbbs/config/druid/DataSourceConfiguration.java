package com.chengbinbbs.config.druid;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangcb on 2017/3/6.
 */
@Configuration
public class DataSourceConfiguration {

    private static Log logger = LogFactory.getLog(DataSourceConfiguration.class);

    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

//    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod="init")
//    @Primary
//    @ConfigurationProperties(prefix = "datasource.write")
//    public DataSource writeDataSource() {
//        logger.info("-------------------- writeDataSource init ---------------------");
//        return DataSourceBuilder.create().type(dataSourceType).build();
//    }

    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod="init")
    @Primary
    public DataSource writeDataSource(@Autowired DBConfig dbConfig)  throws SQLException{
//        DruidXADataSource druidXADataSource=new DruidXADataSource();
//        druidXADataSource.setDriverClassName(dbConfig.getDriverClassName());
//        druidXADataSource.setUrl(dbConfig.getUrl());
//        druidXADataSource.setPassword(dbConfig.getPassword());
//        druidXADataSource.setUsername(dbConfig.getUsername());
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dbConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dbConfig.getPassword());
        mysqlXaDataSource.setUser(dbConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("writeDataSource");

        xaDataSource.setMinPoolSize(dbConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dbConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dbConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dbConfig.getBorrowConnectionTimeout());
        /** login-timeout java数据库连接池，最大可等待获取datasouce的时间 **/
        xaDataSource.setLoginTimeout(dbConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dbConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dbConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(dbConfig.getValidationQuery());
        return xaDataSource;
    }

    /**
     * 有多少个从库就要配置多少个
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "datasource.read1")
    public DataSource readDataSourceOne() {
        logger.info("-------------------- readDataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "datasource.read2")
    public DataSource readDataSourceTwo() {
        logger.info("-------------------- readDataSourceTwo init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("readDataSources")
    public List<DataSource> readDataSources(){
        List<DataSource> dataSources=new ArrayList<>();
        dataSources.add(readDataSourceOne());
        dataSources.add(readDataSourceTwo());
        return dataSources;
    }
}
