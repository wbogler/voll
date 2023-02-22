package com.voll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voll.entities.Medico;
import com.voll.records.DadosCadastroMedico;
import com.voll.repositories.Medicorepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	
	@Autowired
	private Medicorepository medicoRepository;
	
	@SuppressWarnings("deprecation")
	public Medico findMedicoById(Long id) {
		return medicoRepository.getById(id);
	}
	
	@Transactional
	public Medico saveNewMedico(DadosCadastroMedico dados) {
		return medicoRepository.save(new Medico(dados));
	}

}
