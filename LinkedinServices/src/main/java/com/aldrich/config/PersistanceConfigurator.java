package com.aldrich.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages={"com.aldrich.dao"})
public class PersistanceConfigurator extends AbstractMongoConfiguration
{	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();        
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/pase_local");
		//ds.setUrl("jdbc:mysql://10.100.10.101:3306/pase");
		ds.setUsername("root");
		ds.setPassword("12345");
		//ds.setPassword("p@s3$123");
		
		return ds;
	}

	@Bean(name="txManager")	
	public HibernateTransactionManager transactionManager(SessionFactory sf)
	{
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		return htm;
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.aldrich.entity"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public MongoTemplate mongoTemplate() throws Exception 
	{
		//remove _class
		MappingMongoConverter converter = new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
			
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);
					
		return mongoTemplate;
	}

	@Override
	protected String getDatabaseName() 
	{
		return "pase_local";
	}	

	@Override
	@Bean
	public Mongo mongo() throws Exception
	{
		return new MongoClient("127.0.0.1");
	}		
}
