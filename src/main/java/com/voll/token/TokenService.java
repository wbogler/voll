package com.voll.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.voll.entities.Usuario;

@Service
public class TokenService {
	
	String secretKey = "123";
	
	public String gerarToken(Usuario usuario) {
		try {
			Algorithm algorith = Algorithm.HMAC256(secretKey);
			String token = JWT.create()
							.withIssuer("voll.med")
							.withSubject(usuario.getLogin())
							.withExpiresAt(dataExpiracao())
							.sign(algorith);
			return token;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar o token");
		}
	}
	
	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
