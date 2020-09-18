package com.mderyol.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mderyol.api.enumeration.Parameters;
import com.mderyol.api.enumeration.TodoTypeEnum;

@Component
public class AInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("AInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		if (!TodoTypeEnum.M2M.getValue().equals(authType)) {
			return true;
		}
		// Eğer authType:M2M ise ve clientId/clientSecret query'den gelmiş ise bunlar
		// header'a taşınacak [A]
		String clientId = request.getParameter(Parameters.CLIENT_ID.getName());
		if (StringUtils.hasText(clientId)) {
			response.setHeader(Parameters.CLIENT_ID.getName(), clientId);
		}
		String clientSecret = request.getParameter(Parameters.CLIENT_SECRET.getName());
		if (StringUtils.hasText(clientSecret)) {
			response.setHeader(Parameters.CLIENT_SECRET.getName(), clientSecret);
		}
		return true;
	}
}
