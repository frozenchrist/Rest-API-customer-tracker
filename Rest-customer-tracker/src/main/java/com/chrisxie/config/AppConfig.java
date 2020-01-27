package com.chrisxie.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.chrisxie")
@PropertySource("classpath:app-config.properties")
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource dataSource() {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		
		dataSource.setUser(env.getProperty("jdbc.user"));
		
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		System.out.println(dataSource.getJdbcUrl());
		
		System.out.println(dataSource.getDriverClass());
		
		System.out.println(dataSource.getUser());
		
		
		dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		
		dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		
		dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		
		dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
		
		
		
		System.out.println(dataSource.getInitialPoolSize());
		
		System.out.println(dataSource.getMinPoolSize());
		
		System.out.println(dataSource.getMaxPoolSize());
		
		System.out.println(dataSource.getMaxIdleTime());
		
		return dataSource;
	}
	
	
	public Properties hibernateProperties() {
		
		Properties properties = new Properties();
		
		properties.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect") );
		
		properties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql") );
		
		properties.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql") );
		
		return properties;
		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource());
		
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		return sessionFactory;
		
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager() {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		
		transactionManager.setSessionFactory(sessionFactory().getObject());
		
		return transactionManager;
	}
	

}
