package com.mkyong.config.db;

// from baeldung com
//@EnableJpaRepositories(basePackages = {"com.baeldung.persistence.dao", "com.baeldung.springpagination.repository"})
//@Configuration
//@EnableTransactionManagement
//@PropertySource({ "classpath:persistence-${envTarget:h2}.properties" })
//@ComponentScan(basePackages = { "com.baeldung.persistence", "com.baeldung.springpagination" })
//@EnableJpaRepositories(basePackages = {"com.mkyong.dao"})
public class PersistenceConfig {

//    @Autowired
//    private Environment env;
//
//    public PersistenceConfig() {
//        super();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[] { "com.baeldung.persistence.model", "com.baeldung.springpagination.model" });
//
//        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        // vendorAdapter.set
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
//        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
//        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
//        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));
//
//        return dataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    final Properties additionalProperties() {
//        final Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
//        return hibernateProperties;
//    }

}