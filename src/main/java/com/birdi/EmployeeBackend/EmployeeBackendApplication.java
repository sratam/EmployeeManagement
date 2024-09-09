package com.birdi.EmployeeBackend;

import com.birdi.EmployeeBackend.Model.Employee;
import com.birdi.EmployeeBackend.Repo.EmployeeRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class EmployeeBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeBackendApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(EmployeeBackendApplication.class);
//	}

}
