package com.voll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.entities.Medico;
import com.voll.records.AtualizarMedico;
import com.voll.records.DadosCadastroMedico;
import com.voll.records.ListagemMedicos;
import com.voll.repositories.Medicorepository;
import com.voll.services.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("medicos")
public class MedicoController {
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private Medicorepository medicoRepository;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Medico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		return ResponseEntity.ok(medicoService.saveNewMedico(dados));
	}
	
	@GetMapping("/listar")
	public Page<Object> listarMedicos(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
		return medicoRepository.findAll(paginacao).map(ListagemMedicos::new);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Medico> atualizarMedico(@RequestBody @Valid AtualizarMedico dados){
		medicoService.atualizarMedico(dados);
		return ResponseEntity.ok(medicoService.getById(dados.id()));
		
	}
}
