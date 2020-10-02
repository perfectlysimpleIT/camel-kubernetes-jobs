package com.psit.poc.camel.k8s;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.Uri;
import org.apache.camel.component.kubernetes.KubernetesOperations;

/**
 * 
 */
public class ListJobRoute extends RouteBuilder {

	public static final String FROM = "direct:" + ListJobRoute.class.getSimpleName();

	@Inject
	@Uri("log:output")
	private Endpoint resultEndpoint;

	@Override
	public void configure() {

		from(FROM)
			.routeId("kubernetes-joblist-client")
			.toF("kubernetes-job:///dummyHost?kubernetesClient=#myKubernetesClient&operation=" + KubernetesOperations.LIST_JOB)
			.log("We currently have ${body.size()} jobs.")
			.to(resultEndpoint);
	}

}
