package com.mderyol.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mderyol.api.enumeration.TodoTypeEnum;
import com.mderyol.api.exceptions.ResourceNotFoundException;
import com.mderyol.api.intf.ITokenProvider;

@Component
public class CInterceptor implements HandlerInterceptor {

	@Autowired
	ITokenProvider tokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		if (TodoTypeEnum.END_USER.getValue().equals(authType) || TodoTypeEnum.ADMIN.getValue().equals(authType)) {
			// Eğer authType:[EndUser | Admin] ise JWT kontrolü yapılacak (gerçek bir token
			// mı) [C]
			String bearerToken = request.getHeader("Authorization");
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer ")
					&& tokenProvider.validateToken(bearerToken.substring(7, bearerToken.length()))) {
				System.out.println("valid token");
			} else {

				new ResourceNotFoundException("TOKEN", "Invalid", "token invalid");
			}
		}
		return true;
	}
}
