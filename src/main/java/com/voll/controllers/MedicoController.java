package com.voll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.entities.Medico;
import com.voll.records.DadosCadastroMedico;
import com.voll.services.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Medico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		return ResponseEntity.ok(medicoService.saveNewMedico(dados));
	}

}
