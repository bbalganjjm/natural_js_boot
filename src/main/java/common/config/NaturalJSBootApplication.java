package common.config;

import common.mybatis.MapWrapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2020.11.25
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages = { "common" })
@ComponentScan(basePackages = { "common" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
@PropertySource(value = { "classpath:config/common/data.properties",
        "classpath:config/common/file.properties" }, ignoreResourceNotFound = true)
public class NaturalJSBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaturalJSBootApplication.class, args);
    }

    /**
     * Properties
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Transaction
     */
    @Autowired
    private DataSource dataSource;
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * Mybatis
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setObjectWrapperFactory(new MapWrapperFactory());
            }
        };
    }
}
