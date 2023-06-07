package com.raj.properties.rajproperties.db1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.raj.properties.rajproperties.db1.repositories",
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DB1JpaConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }



    @Bean(name = "db1DataSource")
    public DataSource db1DataSource() {
        return db1DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name="db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier("db1DataSource") DataSource db1DataSource) {
        // Configure and return the EntityManagerFactory for db1
        return builder
                .dataSource(db1DataSource)
                .packages("com.raj.properties.rajproperties.db1.models")
                .persistenceUnit("db1")
                .properties(hibernateProperties())
                .build();
    }

    @Bean(name="db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager(
            @Qualifier("db1EntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        // Configure and return the TransactionManager for db1
        return new JpaTransactionManager(db1EntityManagerFactory);
    }


    @Bean(name = "db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(@Qualifier("db1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    private Map hibernateProperties() {
        HashMap<String, Object> properties = new HashMap<String, Object>();
        //properties.put("hibernate.hbm2ddl.auto", "create");
        return properties;
    }
}