package br.com.springarchetype.confs;

import java.util.Properties;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Profile("dev")
public class JpaDevConfiguration extends JpaConfiguration {
	
	@Bean
	@Override
	public DataSource dataSource(){
		//Obtendo datasource configurado no arquivo context.xml do tomcat 
		JndiDataSourceLookup dataSource = new JndiDataSourceLookup();
	    dataSource.setResourceRef(true);
	    return dataSource.getDataSource("jdbc/prod");	
	    
		
//		ComboPooledDataSource cpds = new ComboPooledDataSource();
//		try {
//			cpds.setDriverClass("com.mysql.jdbc.Driver");
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		cpds.setJdbcUrl("jdbc:mysql://localhost/teste");
//		cpds.setUser("root");
//		cpds.setPassword("f10121426");
//		cpds.setMinPoolSize(5);
//		cpds.setAcquireIncrement(5);
//		cpds.setMaxPoolSize(20);
//		cpds.setMaxStatements(180);
//		return cpds;
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
