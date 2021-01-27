package com.justryoo.manager;

import com.ckfinder.connector.ConnectorServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableConfigurationProperties
@MapperScan(basePackages = "com.justryoo.manager.dao")
public class ManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ConnectorServlet connectorServlet = new ConnectorServlet();
		ServletRegistrationBean servletRegistrationBean =
				new ServletRegistrationBean(connectorServlet,"/ckfinder/core/connector/java/connector.java");
		servletRegistrationBean.addInitParameter("XMLConfig","config.xml");
		servletRegistrationBean.addInitParameter("debug","false");
		return servletRegistrationBean;
	}

}
