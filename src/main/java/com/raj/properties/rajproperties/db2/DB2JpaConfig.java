package com.raj.properties.rajproperties.db2;

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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.raj.properties.rajproperties.db2.repositories",
        entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class DB2JpaConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }



    @Bean(name = "db2DataSource")
    public DataSource db2DataSource() {
        return db2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name="db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier("db2DataSource") DataSource db2DataSource) {
        // Configure and return the EntityManagerFactory for db2
        return builder
                .dataSource(db2DataSource)
                .packages("com.raj.properties.rajproperties.db2.entities")
                .persistenceUnit("db2")
                .build();
    }

    @Bean(name="db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(
            @Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory) {
        // Configure and return the TransactionManager for db2
        return new JpaTransactionManager(db2EntityManagerFactory);
    }


    @Bean(name = "db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}