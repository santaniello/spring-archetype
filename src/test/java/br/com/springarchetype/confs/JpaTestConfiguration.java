package br.com.springarchetype.confs;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Profile("test")
public class JpaTestConfiguration extends JpaConfiguration {
	
	@Bean	
	@Override
	public DataSource dataSource(){
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass("com.mysql.jdbc.Driver");
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/teste");
			cpds.setUser("root");
			cpds.setPassword("f10121426");
			cpds.setMinPoolSize(5);
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
			cpds.setMaxStatements(180);
			return cpds;
		} catch (PropertyVetoException e) {
			throw new RuntimeException("Erro ao criar Data Source!");
		}
	}
	
	@Bean
	@Override
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}	
}
