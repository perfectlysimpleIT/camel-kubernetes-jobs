package com.psit.poc.camel.k8s;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kubernetes.KubernetesOperations;

import io.fabric8.kubernetes.client.KubernetesClientException;

public class ListPodsRoute extends RouteBuilder {

	public static final String FROM = "direct:" + ListPodsRoute.class.getSimpleName();

    @Override
    public void configure() {
        from(FROM)
        	.routeId("kubernetes-pods-client")
            .onException(KubernetesClientException.class).handled(true)
                .log(LoggingLevel.ERROR, "${exception.message}")
                .log("Stopping the Kubernetes route...")
                .to("controlbus:route?routeId=kubernetes-client&action=stop&async=true&loggingLevel=DEBUG")
                .end()
            .to("kubernetes-pods://{{kubernetes-master-url}}?oauthToken={{kubernetes-oauth-token:}}&operation=" + KubernetesOperations.LIST_PODS_OPERATION)
            .log("We currently have ${body.size()} pods.")
            ;
    }
    
}