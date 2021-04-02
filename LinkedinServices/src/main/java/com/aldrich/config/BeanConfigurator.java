package com.aldrich.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceConfigurator.class,PersistanceConfigurator.class})
public class BeanConfigurator 
{
	
}
