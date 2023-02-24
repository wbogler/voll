package com.voll.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoErros {
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErros(){
		return ResponseEntity.notFound().build();
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException erro) {
		var erros = erro.getFieldErrors();
		
		return ResponseEntity.badRequest().body(
				erros.stream().map(DadosErroValidacao::new).toList()
				);
	}
	
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
