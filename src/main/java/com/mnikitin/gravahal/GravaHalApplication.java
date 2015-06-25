package com.mnikitin.gravahal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by mnikitin
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class GravaHalApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GravaHalApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		System.out.println("Registered beans:");
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}

	}
}
