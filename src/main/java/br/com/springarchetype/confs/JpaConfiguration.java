package br.com.springarchetype.confs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe que será usada como classe de configuração do JPA.
 */

@EnableTransactionManagement 
@EnableJpaRepositories(basePackages  = {"br.com.springarchetype.repositories"}, 
			 entityManagerFactoryRef =  "entityManagerFactory", 
		       transactionManagerRef =  "transactionEntityManager")
public abstract class JpaConfiguration {
	
	@Bean	
	public abstract DataSource dataSource();
			
	@Bean
	public abstract Properties additionalProperties();	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties additionalProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaProperties(additionalProperties);
		factoryBean.setPackagesToScan("br.com.springarchetype.models");
		return factoryBean;
	}
		
	@Bean(name="transactionEntityManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }	
}
