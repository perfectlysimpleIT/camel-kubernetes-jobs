package com.psit.poc.camel.k8s;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.PropertyInject;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class BeanFactory {
	
	@PropertyInject("kubernetes-master-url")
	String masterUrl;
	
	@Produces
	@ApplicationScoped
	@Named("myKubernetesClient")
	public KubernetesClient produceDefaultKubernetesClient(){
		Config config = new ConfigBuilder()
		        .withMasterUrl(masterUrl)
		        .build();
		KubernetesClient client = new DefaultKubernetesClient(config);
		return client;
	}

}
