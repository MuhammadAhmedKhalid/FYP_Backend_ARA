package com.springboot.fyp.root.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.fyp.root.service.JWT_Utils;
import com.springboot.fyp.root.service.MongoAuthUserDetailService;

@Configuration
public class JWT_Filters extends OncePerRequestFilter {
	
	@Autowired
	MongoAuthUserDetailService userDetailService;
	
	@Autowired
	JWT_Utils jwt_Utils;

	@Override
	protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain)
			throws javax.servlet.ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		String username = null;
		String jwt = null;
		
		if (header != null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
			
			try {
				username = jwt_Utils.extractUsername(jwt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}
}
