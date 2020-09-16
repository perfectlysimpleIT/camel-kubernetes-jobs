package com.psit.poc.camel.k8s;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.cdi.Main;
import org.apache.camel.component.properties.PropertiesComponent;

/**
 * https://kubernetes.io/docs/tasks/administer-cluster/access-cluster-api/
 * 
 * @author emmersonmiranda
 *
 */
public class MainApp {

	public static void main(String... args) throws Exception {
		Main mainApp = new Main();
		mainApp.run(args);
	}

	@Produces
	@ApplicationScoped
	@Named("properties")
	PropertiesComponent properties() {
		PropertiesComponent component = new PropertiesComponent();
		component.setLocation("classpath:application.properties");
		return component;
	}

}
