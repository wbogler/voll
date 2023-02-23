package com.voll.records;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedico(
							
							@NotNull Long id,
							String nome, 
							String telefone, 
							DadosEndereco endereco
							) {

}
