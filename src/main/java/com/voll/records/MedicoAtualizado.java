package com.voll.records;

import com.voll.entities.Endereco;
import com.voll.entities.Medico;
import com.voll.enums.Especialidade;

public record MedicoAtualizado(
								Long id,
								String nome,
								String email,
								String crm,
								String telefone,
								Especialidade especialidade,
								Endereco endereco
		) {
	
	public MedicoAtualizado(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
	}

}
