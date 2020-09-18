package com.mderyol.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mderyol.api.enumeration.Parameters;
import com.mderyol.api.enumeration.TodoTypeEnum;
import com.mderyol.api.service.ClientService;

@Component
public class BInterceptor implements HandlerInterceptor {

	@Autowired
	ClientService clientService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("BInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		if (!TodoTypeEnum.M2M.getValue().equals(authType)) {
			return true;
		}
		// Eğer authType:M2M ise clientId kontrolü yapılacak (gerçek bir client mı,
		// db'de sadece client1 ve client2 var) [B]
		String clientId = request.getParameter(Parameters.CLIENT_ID.getName());
		String clientSecret = request.getParameter(Parameters.CLIENT_SECRET.getName());
		if (StringUtils.hasText(clientId) && StringUtils.hasText(clientSecret)) {
			clientService.findByClient(clientId, clientSecret);
		}
		return true;
	}
}
