package com.voll.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.voll.entities.Medico;

public interface Medicorepository extends JpaRepository<Medico, Long>{

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

}
