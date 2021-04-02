package com.aldrich.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aldrich.config.BeanConfigurator;
import com.aldrich.service.LinkedinAboutUsService;
import com.aldrich.service.LinkedinEmployeeService;
import com.aldrich.service.LinkedinJobsService;
import com.aldrich.service.LinkedinPeopleStatsService;
import com.aldrich.service.LinkedinPostsDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=BeanConfigurator.class)
//@Ignore
public class LinkedinTest 
{


	@Autowired
	LinkedinEmployeeService linkedinEmployeeService;

	@Autowired
	LinkedinAboutUsService linkedinAboutUsService;

	@Autowired
	LinkedinJobsService linkedinJobsService;

	@Autowired
	LinkedinPeopleStatsService linkedinPeopleStatsService;

	@Autowired
	LinkedinPostsDataService linkedinPostsDataService;

	//@Ignore
	@Test
	public void runLikedinEmployeeService()
	{
		try{
			linkedinEmployeeService.runService();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	


	//@Ignore
	@Test
	public void runLinkedinAboutUsServicee()
	{
		try{	
			linkedinAboutUsService.runService();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	


	//@Ignore
	@Test
	public void runLinkedinJobsService()
	{
		try	{
			linkedinJobsService.runService();
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
	}	


	//@Ignore
	@Test
	public void runLinkedinPeopleStatsService()
	{
		try{
			linkedinPeopleStatsService.runService();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	


	//@Ignore
	@Test
	public void runLinkedinPostsDataServicee()
	{
		try	{
			linkedinPostsDataService.runService();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	

	}

