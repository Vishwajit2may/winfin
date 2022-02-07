package com.example.microservices.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.binder.http.HttpServletRequestTagsProvider;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator getweayRouter(RouteLocatorBuilder builder){
		
		 
		/*
		 * Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get").
				filters(f->f.addRequestHeader("MyHeader", "MyHeader")
							.addRequestParameter("PARAM1", "PARAM_VAL")).uri("http://httpbin.org:80");
		 * */
		return builder.routes()
				.route(p -> p.path("/get").
						filters(f->f.addRequestHeader("MyHeader", "MyHeader")
									.addRequestParameter("PARAM1", "PARAM_VAL"))
						.uri("http://httpbin.org:80"))
				//.route(p-> p.path("/auth/**").uri("lb://security-service"))
				.route(p-> p.path("/currency-exchange/**").uri("lb://currency-exchange-service"))
				.route(p-> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service"))
				.route(p-> p.path("/currency-conversion-new/**")
						.filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion-service"))
				.route(p-> p.path("/currency-conversion/**").uri("lb://currency-conversion-service"))
				.route(p-> p.path("/orders/**").uri("lb://customer-service"))
				.build();
	}
}
