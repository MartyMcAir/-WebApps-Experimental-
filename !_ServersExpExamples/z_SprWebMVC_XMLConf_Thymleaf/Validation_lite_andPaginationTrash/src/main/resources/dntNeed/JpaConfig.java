package com.devcases.usermanager.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// искать классы репозитория в указанном пакете (*) для внедрения соответсвующего кода во время выполнения.
@EnableJpaRepositories(basePackages = {"com.devcases.usermanager"})
// чтобы Spring Data генерировал код, для управления транзакциями во время выполнения.
@EnableTransactionManagement
public class JpaConfig {
    // В этом классе первый метод создаёт экземпляр EntityManagerFactory для управления Persistence Unit нашей SalesDB
// (это имя указано выше в persistence.xml).
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("h2Base");

        return factoryBean;
    }

    // создаёт экземпляр JpaTransactionManager для EntityManagerFactory, созданный методом ранее.
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}