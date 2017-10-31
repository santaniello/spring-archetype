package br.com.springarchetype.confs;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.stagemonitor.core.Stagemonitor;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Stagemonitor.shutDown();
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Stagemonitor.init();
		
	}

}
