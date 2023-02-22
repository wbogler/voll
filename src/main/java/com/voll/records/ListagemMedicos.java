package com.voll.records;

import com.voll.entities.Medico;
import com.voll.enums.Especialidade;

public record ListagemMedicos(
								String nome,
								String email, 
								String crm, 
								Especialidade especialidade
		) {
	
	public ListagemMedicos(Medico medico) {
		this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
	
}
