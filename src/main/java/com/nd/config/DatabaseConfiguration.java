package com.nd.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 类名称：
 * 类描述：
 * 创建人：newtonk
 * 创建日期：2016/9/3 0003
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.nd")
public class DatabaseConfiguration{

    private static final Logger logger = Logger.getLogger(WebMvcConfig.class);
    //数据源配置  为什么DruidDataSource这么慢
//    @Bean(initMethod = "init", destroyMethod = "close")
//    public DataSource dataSource() throws SQLException, IOException {
//        Resource resource = new ClassPathResource("config/jdbc.properties");
//        Properties props = PropertiesLoaderUtils.loadProperties(resource);
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(props.getProperty("jdbc.url"));
//        dataSource.setName(props.getProperty("jdbc.username"));
//        dataSource.setPassword(props.getProperty("jdbc.password"));
//        dataSource.setDriverClassName(props.getProperty("jdbc.driver-class-name"));
//        /* 配置监控统计拦截的filters*/
//        dataSource.setFilters("stat");
//        /* 配置初始化大小、最小、最大 */
//        dataSource.setInitialSize(1);
//        dataSource.setMaxActive(120);
//        dataSource.setMinIdle(30);
//        /*配置获取连接等待超时的时间*/
//        dataSource.setMaxWait(1000);
//        /*配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒*/
//        dataSource.setTimeBetweenConnectErrorMillis(60000);
//        /*配置一个连接在池中最小生存的时间，单位是毫秒*/
//        dataSource.setMinEvictableIdleTimeMillis(300000);
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);
//        /* 打开PSCache，并且指定每个连接上PSCache的大小*/
//        dataSource.setPoolPreparedStatements(false);
//        dataSource.setMaxOpenPreparedStatements(0);
//        return  dataSource;
//    }
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws Exception {
        Resource resource = new ClassPathResource("config/jdbc.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(props.getProperty("jdbc.url"));
        dataSource.setUser(props.getProperty("jdbc.username"));
        dataSource.setPassword(props.getProperty("jdbc.password"));
        dataSource.setDriverClass(props.getProperty("jdbc.driver-class-name"));
        /* 配置初始化大小、最小、最大 */
        dataSource.setInitialPoolSize(5);
        dataSource.setMinPoolSize(30);
        dataSource.setMaxPoolSize(120);
        //一次性获取连接数
        dataSource.setAcquireIncrement(10);
        //最大空闲时间
        dataSource.setMaxIdleTime(1800);
        //隔多少秒检查空闲连接
        dataSource.setIdleConnectionTestPeriod(30);
        //申请新连接等待时间
        dataSource.setCheckoutTimeout(0);
        dataSource.setMaxStatements(0);
        //异步线程数执行jdbc
        dataSource.setNumHelperThreads(100);
        /*因性能消耗大请只在需要的时候使用它。如果设为true那么在每个connection提交的
        时候都将校验其有效性。建议使用idleConnectionTestPeriod或automaticTestTable
        等方法来提升连接测试的性能。Default: false */
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setPreferredTestQuery("select 1");
        return  dataSource;
    }

    public JdbcTemplate jdbcTemplate(DataSource dataSource) throws Exception{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return  jdbcTemplate;
    }
    //sessionFactory
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) throws Exception {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.nd");
        logger.info("Before LocalContainerEntityManagerFactoryBean.afterPropertiesSet():::"+factory.getJpaPropertyMap());
        factory.afterPropertiesSet();
        logger.info("After LocalContainerEntityManagerFactoryBean.afterPropertiesSet():::"+factory.getJpaPropertyMap());
        return factory.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws Exception {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
