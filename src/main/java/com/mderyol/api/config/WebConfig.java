package com.mderyol.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mderyol.api.interceptor.AInterceptor;
import com.mderyol.api.interceptor.BInterceptor;
import com.mderyol.api.interceptor.CInterceptor;
import com.mderyol.api.interceptor.DInterceptor;
import com.mderyol.api.interceptor.EInterceptor;
import com.mderyol.api.interceptor.FInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	AInterceptor aInterceptor;
	@Autowired
	BInterceptor bInterceptor;
	@Autowired
	CInterceptor cInterceptor;
	@Autowired
	DInterceptor dInterceptor;
	@Autowired
	EInterceptor eInterceptor;
	@Autowired
	FInterceptor fInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(aInterceptor);
		registry.addInterceptor(bInterceptor);
		registry.addInterceptor(cInterceptor);
		registry.addInterceptor(dInterceptor);
		registry.addInterceptor(eInterceptor);
		registry.addInterceptor(fInterceptor);
	}
}
