package com.hashi.config;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("cloud")
public class CloudDataSourceConfiguration {
	@Autowired
	ApplicationConfig config;

	@Bean
	public Cloud cloud() {
		return new CloudFactory().getCloud();
	}

	@Bean
	public DataSource dataSource() {

		DataSource dataSource = new DataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setDriverClassName(config.getCloudDbDriver());
		dataSource.setUrl(config.getCloudDbUrl());
		dataSource.setUsername(config.getCloudDbUsername());
		dataSource.setPassword(config.getCloudDbPassword());
		dataSource.setMaxActive(3);
		dataSource.setInitialSize(2);
		dataSource.setMinIdle(3);
	    dataSource.setTimeBetweenEvictionRunsMillis(5000);
	    dataSource.setMinEvictableIdleTimeMillis(50000);
	    dataSource.setTestOnBorrow(true);
	    dataSource.setTestWhileIdle(true);
	    dataSource.setValidationInterval(5000);		
		dataSource.setValidationQuery("SELECT 1");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setShowSql(false);
		return jpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
		lemfb.setDataSource(dataSource());
		lemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lemfb.setPackagesToScan(config.getPackageToScan());
		return lemfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(emf);
		tm.setDataSource(this.dataSource());
		return tm;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
