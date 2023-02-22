package com.voll.records;

import com.voll.enums.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
		
									@NotBlank String nome, 
									@NotBlank @Email String email,
									@NotBlank String telefone,
									@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, // digitos de 4 a 6 dígitos em pattern
									@NotNull Especialidade especialidade, 
									@Valid DadosEndereco endereco) {

}
