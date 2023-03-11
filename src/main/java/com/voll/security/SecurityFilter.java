package com.voll.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.voll.repositories.UsuarioRepository;
import com.voll.token.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository userReposioty;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var tokenJwt = recuperarToken(request);
		if (tokenJwt != null) {
			var usuario = userReposioty.findByLogin(tokenService.recuperarSubject(tokenJwt));
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			return authHeader.substring(7);
		}
		return null;
	}

}
