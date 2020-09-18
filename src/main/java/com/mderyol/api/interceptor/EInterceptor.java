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
public class EInterceptor implements HandlerInterceptor {

	@Autowired
	ClientService clientService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("EInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		if (!TodoTypeEnum.M2M.getValue().equals(authType)) {
			return true;
		}
		// Eğer authType:M2M ve clientId:client1 ise, JWT cridential'lara "todo:delete"
		// eklenecek (normalde bu dao katmanında çekilirdi, burada hard-coded) [E]
		String clientId = request.getParameter(Parameters.CLIENT_ID.getName());
		if (StringUtils.hasText(clientId) && "client1".equals(clientId)) {
			clientService.addRole(clientId, "todo:delete");
		}
		return true;
	}
}
