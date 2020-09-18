package com.mderyol.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mderyol.api.enumeration.TodoTypeEnum;
import com.mderyol.api.intf.ITokenProvider;
import com.mderyol.api.service.CustomUserDetailsService;

@Component
public class FInterceptor implements HandlerInterceptor {

	@Autowired
	CustomUserDetailsService customerService;

	@Autowired
	ITokenProvider tokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("FInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		if (!TodoTypeEnum.END_USER.getValue().equals(authType)) {
			return true;
		}
		// Eğer authType:EndUser ise cridential'lara "todo:add" eklenecek (sosyal
		// medyadan gelen ekleyebilir gibi) [F]
		Long userId = null;
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer ")) {
			userId = tokenProvider.getUserIdFromToken(bearerToken.substring(7, bearerToken.length()));
			customerService.addRole(userId, "todo:add");
		}
		return true;
	}
}
