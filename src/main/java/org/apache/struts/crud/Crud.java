package org.apache.struts.crud;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@SpringBootApplication
@EnableWebSecurity
public class Crud {

    public static void main (String [] args)
    {
        SpringApplication.run(Crud.class,args);
    }


    @Bean
    public FilterRegistrationBean struts2()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new StrutsPrepareAndExecuteFilter());
        filterRegistrationBean.addUrlPatterns("*.action");

        return filterRegistrationBean;
    }
}
