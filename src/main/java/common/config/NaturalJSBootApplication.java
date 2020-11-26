package common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author KIM HWANG MAN( bbalganjjm@gmail.com )
 * @since 2020.11.25
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = { "common" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) })
@PropertySource(value = { "classpath:config/common/data.properties",
        "classpath:config/common/file.properties" }, ignoreResourceNotFound = true)
public class NaturalJSBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaturalJSBootApplication.class, args);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
