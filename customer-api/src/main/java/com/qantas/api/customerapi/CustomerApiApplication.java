package com.qantas.api.customerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class CustomerApiApplication extends WebMvcConfigurerAdapter{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

	}
	@LoadBalanced
	public static void main(String[] args) {
		SpringApplication.run(CustomerApiApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.qantas.api.customerapi.controller"))
				.paths(PathSelectors.any())
				.build();
	}

//	@Component
//	@Primary
//	public class CustomObjectMapper extends ObjectMapper {
//		public CustomObjectMapper() {
//			setSerializationInclusion(JsonInclude.Include.NON_NULL);
//			configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			enable(SerializationFeature.INDENT_OUTPUT);
//		}
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder()
//				.title("Springfox petstore API")
//				.description("description")
//				.contact(new Contact("name", "url", "email"))
//				.license("Apache License Version 2.0")
//				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
//				.version("2.0")
//				.build();
//	}

	@Bean(name = "h2WebServer", initMethod="start", destroyMethod="stop")
	public org.h2.tools.Server h2WebServer() throws SQLException {
		return org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
	}


	@Bean(initMethod="start", destroyMethod="stop")
	@DependsOn(value = "h2WebServer")
	public org.h2.tools.Server h2Server() throws SQLException {
		return org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
