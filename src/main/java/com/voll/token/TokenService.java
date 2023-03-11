package com.voll.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.voll.entities.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	String secretKey;
	
	public String gerarToken(Usuario user) {
		try {
			Algorithm algorith = Algorithm.HMAC256(secretKey);
			return JWT.create()
							.withIssuer("voll.med")
							.withSubject(user.getUsername())
							.withExpiresAt(dataExpiracao())
							.sign(algorith);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar o token");
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	
	public String recuperarSubject(String token) {
		try {
			Algorithm algorith = Algorithm.HMAC256(secretKey);
			return JWT.require(algorith)
				   .withIssuer("voll.med")
				   .build()
				   .verify(token)
				   .getSubject();
		} catch (Exception e) {
			throw new JWTVerificationException("Token inválido ou expirado");
		}
	}
}
