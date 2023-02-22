package com.voll.records;

import com.voll.enums.Especialidade;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereço) {

}
