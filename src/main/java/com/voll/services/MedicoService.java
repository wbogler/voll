package com.voll.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voll.entities.Medico;
import com.voll.records.DadosCadastroMedico;
import com.voll.records.ListagemMedicos;
import com.voll.repositories.Medicorepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	private Medicorepository medicoRepository;
	
	@Transactional
	public Medico saveNewMedico(DadosCadastroMedico dados) {
		return medicoRepository.save(new Medico(dados));
	}
	
	public List<ListagemMedicos> listaMedicos(){
		return medicoRepository.findAll().stream().map(ListagemMedicos::new).toList();
	}

}
