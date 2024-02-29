package com.nhnacademy.edu.certificateissueservice.config;

import com.nhnacademy.edu.certificateissueservice.properties.DatabaseProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 02/05/2023
 */
@Profile("!test")
@RequiredArgsConstructor
@Configuration
public class DatabaseConfig {

    private final DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(databaseProperties.getDriverClassName());
        basicDataSource.setUrl(databaseProperties.getUrl());
        basicDataSource.setUsername(databaseProperties.getUsername());
        basicDataSource.setPassword(databaseProperties.getPassword());
        basicDataSource.setInitialSize(databaseProperties.getInitialSize());
        basicDataSource.setMaxTotal(databaseProperties.getMaxTotal());
        basicDataSource.setMinIdle(databaseProperties.getMinIdle());
        basicDataSource.setMaxIdle(databaseProperties.getMaxIdle());
        basicDataSource.setTestOnBorrow(databaseProperties.isTestOnBorrow());
        if(databaseProperties.isTestOnBorrow()) {
            basicDataSource.setValidationQuery(basicDataSource.getValidationQuery());
        }
        return basicDataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        initializer.setDatabaseCleaner(databaseCleaner());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript());
        return populator;
    }

    private DatabasePopulator databaseCleaner() {
        final ResourceDatabasePopulator databaseCleaner = new ResourceDatabasePopulator();
        databaseCleaner.addScript(dropScript());
        return databaseCleaner;
    }

    private Resource schemaScript() {
        return new ClassPathResource("script/schema.sql");
    }

    private Resource dropScript() {
        return new ClassPathResource("script/drop.sql");
    }
}
