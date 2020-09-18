package com.mderyol.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mderyol.api.enumeration.Parameters;
import com.mderyol.api.enumeration.TodoTypeEnum;
import com.mderyol.api.intf.ITokenProvider;

@Component
public class DInterceptor implements HandlerInterceptor {

	@Autowired
	ITokenProvider tokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("DInterceptor - Pre Handle method is Calling");
		String authType = request.getHeader("x-auth-type");
		// Eğer authType:M2M ise header'da "x-auth-id: M2M/clientId" olacak şekilde key
		// eklenecek (clientId header'da gelen) [D]

		// Eğer authType:EndUser ise header'da "x-auth-id: Social/socialId" olacak
		// şekilde key eklenecek (socialId JWT'den alınacak user id vs) [D]

		// Eğer authType:Admin ise header'da "x-auth-id: Admin/auth0Id" olacak şekilde
		// key eklenecek (auth0Id JWT'den alınacak id vs, hint: query.user.sub içinde
		// olabilir) [D]

		// Eğer authType:[!M2M & !EndUser & !Admin] değilse headerda "x-auth-id: free"
		// şekilde kayıt eklenecek (loginsiz gibi düşünebilirsiniz) [D]
		Long userId = null;
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("bearer ")) {
			userId = tokenProvider.getUserIdFromToken(bearerToken.substring(7, bearerToken.length()));
		}
		if (TodoTypeEnum.END_USER.getValue().equals(authType) && userId != null) {
			response.setHeader(Parameters.AUTH_ID.getName(), "Social/" + userId);
		} else if (TodoTypeEnum.ADMIN.getValue().equals(authType) && userId != null) {
			response.setHeader(Parameters.AUTH_ID.getName(), "Admin/" + userId);
		} else if (TodoTypeEnum.M2M.getValue().equals(authType)) {
			String clientId = request.getParameter(Parameters.CLIENT_ID.getName());
			if (StringUtils.hasText(clientId)) {
				response.setHeader(Parameters.AUTH_ID.getName(), "M2M/" + clientId);
			}
		} else {
			response.setHeader(Parameters.AUTH_ID.getName(), "free");
		}
		return true;
	}
}
