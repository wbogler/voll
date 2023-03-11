package com.voll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.entities.Usuario;
import com.voll.records.DadosAutenticacao;
import com.voll.records.DadosTokenJWT;
import com.voll.token.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	TokenService tokenService;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()));
		var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}

}
