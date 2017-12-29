package com.niit.Configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[]{WebConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[]{DBConfiguration.class};
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[]{"/"}; //any requests, forward the request to DispatcherServlet
	}
}
