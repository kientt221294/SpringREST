package com.hivetech.SpringREST.config;

import com.hivetech.SpringREST.service.CustomerService;
import com.hivetech.SpringREST.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.hivetech.SpringREST.controller")
@PropertySources({ @PropertySource("classpath:jdbc.properties") })
public class SpringRESTConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        System.out.println("## getDataSource: " + dataSource);

        return dataSource;
    }

    @Bean
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl(getDataSource());
    }
}
