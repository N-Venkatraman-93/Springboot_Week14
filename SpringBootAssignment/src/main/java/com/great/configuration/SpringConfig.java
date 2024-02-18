package com.great.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@ComponentScan("com.gl.MyController")
public class SpringConfig {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		//this is object which finds the front end files-locate
		SpringResourceTemplateResolver resolver=new SpringResourceTemplateResolver();
		//folder
		resolver.setPrefix("/WEB-INF/pages/");
		//extension
		resolver.setSuffix(".html");
		//which container
		resolver.setApplicationContext(applicationContext);
		//type of file
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
	@Bean
	public SpringTemplateEngine templateEngine() {
		//load the file found by the resolver
		SpringTemplateEngine engine=new SpringTemplateEngine();
		//mapping the engine to the resolver
		engine.setTemplateResolver(templateResolver());
		//for accepting the expression language eg: ${temp.name},${message}
		engine.setEnableSpringELCompiler(true);
		return engine;
	}
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver resolver=new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		return resolver;
	}

}
