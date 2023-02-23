package com.voll.records;

import com.voll.entities.Medico;
import com.voll.enums.Especialidade;

public record ListagemMedicos(
								Long id,
								String nome,
								String email, 
								String crm, 
								Especialidade especialidade
		) {
	
	public ListagemMedicos(Medico medico) {
		this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
	
}
