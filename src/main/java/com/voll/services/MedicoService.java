package com.voll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voll.entities.Endereco;
import com.voll.entities.Medico;
import com.voll.records.AtualizarMedico;
import com.voll.records.DadosCadastroMedico;
import com.voll.records.MedicoAtualizado;
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
	
	@Transactional
	public void atualizarMedico(AtualizarMedico dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		if(dados.nome() != null) {
			medico.setNome(dados.nome());
		}
		if(dados.telefone() != null) {
			medico.setTelefone(dados.telefone());
		}
		if(dados.endereco() != null) {
			Endereco enderecoNovo = new Endereco();
			if(dados.endereco().logradouro() != null) {
				enderecoNovo.setLogradouro(dados.endereco().logradouro());
			}
			if(dados.endereco().bairro() != null) {
				enderecoNovo.setBairro(dados.endereco().bairro());
			}
			if(dados.endereco().cep() != null) {
				enderecoNovo.setCep(dados.endereco().cep());
			}
			if(dados.endereco().numero() != null) {
				enderecoNovo.setNumero(dados.endereco().numero());
			}
			if(dados.endereco().complemento() != null) {
				enderecoNovo.setComplemento(dados.endereco().complemento());
			}
			if(dados.endereco().cidade() != null) {
				enderecoNovo.setCidade(dados.endereco().cidade());
			}
			if(dados.endereco().uf() != null) {
				enderecoNovo.setUf(dados.endereco().uf());
			}
			medico.setEndereco(enderecoNovo);
		}
	}

	public MedicoAtualizado getById(Long id) {
		var medico = medicoRepository.getReferenceById(id);
		return new MedicoAtualizado(medico);
	}
	
	@Transactional
	public String desativarMedico(Long id) {
		try {
			var medico = medicoRepository.getReferenceById(id);
			medico.setAtivo(false);
			return "desativado";
		} catch (Exception e) {
			return "não desativado";
		}

	}
}
