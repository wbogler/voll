package com.voll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.voll.entities.Medico;
import com.voll.records.AtualizarMedico;
import com.voll.records.DadosCadastroMedico;
import com.voll.records.ListagemMedicos;
import com.voll.records.MedicoAtualizado;
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
	public ResponseEntity<Medico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = medicoService.saveNewMedico(dados);
		var uri = uriBuilder.path("medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(medicoService.saveNewMedico(dados));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoAtualizado> detalharMedicoporId(@RequestParam Long id){
		var medico = medicoService.getById(id);
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping("/listar")
	public Page<Object> listarMedicos(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao){
		return medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicos::new);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<MedicoAtualizado> atualizarMedico(@RequestBody @Valid AtualizarMedico dados){
		medicoService.atualizarMedico(dados);
		return ResponseEntity.ok(medicoService.getById(dados.id()));
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id){
		return ResponseEntity.noContent().build();
	}
}
