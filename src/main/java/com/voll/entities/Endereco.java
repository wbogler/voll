package com.voll.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Endereco {
	
	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento; 
	private String  cidade;
	private String  uf;

}
