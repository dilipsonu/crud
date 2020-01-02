package org.apache.struts.crud.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("cloud")
public class DataSourceConfigs {

    @Bean
    public Cloud cloud()
    {
        return new CloudFactory().getCloud();
    }

    @Bean
    public DataSource dataSource()
    {
        return cloud().getSingletonServiceConnector(DataSource.class,null);
    }
}
