package com.psit.poc.camel.k8s;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;

public class KubernetesClientMain {

	public static void main(String[] args) {
		Config config = new ConfigBuilder()
		        .withMasterUrl("192.168.64.3:8443")
		        .build();
		try (KubernetesClient client = new DefaultKubernetesClient(config)) {

		    client.pods().inNamespace("default").list().getItems().forEach(
		            pod -> System.out.println(pod.getMetadata().getName())
		    );

		} catch (KubernetesClientException ex) {
		    // Handle exception
		    ex.printStackTrace();
		}
	}

}
