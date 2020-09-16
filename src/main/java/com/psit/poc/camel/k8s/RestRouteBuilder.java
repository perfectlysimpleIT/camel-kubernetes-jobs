package com.psit.poc.camel.k8s;

import org.apache.camel.builder.RouteBuilder;

public class RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration().component("undertow")
			.contextPath("/").host("localhost").port("8080")
				.apiContextPath("/api-doc").apiContextRouteId("api-doc-endpoint")
				.apiProperty("cors", "true");

		rest("/k8s").description("K8S operations.")
			.get("/pod").description("List pods.").route()
				.routeId("LIST-POD-REST-GET").to(ListPodsRoute.FROM).endRest()
			.get("/job")
				.description("List jobs.").route().routeId("LIST-JOB-REST-GET").to(ListJobRoute.FROM).endRest()
			.post("/job")
				.description("Create a simple job.").route().routeId("CREATE-JOB-REST-POST").to(CreateJobRoute.FROM);

	}

}
