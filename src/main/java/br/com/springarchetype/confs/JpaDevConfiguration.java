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
		// Obtendo datasource configurado no arquivo context.xml do tomcat 
		JndiDataSourceLookup dataSource = new JndiDataSourceLookup();
	    dataSource.setResourceRef(true);
	    return dataSource.getDataSource("jdbc/prod");	
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
