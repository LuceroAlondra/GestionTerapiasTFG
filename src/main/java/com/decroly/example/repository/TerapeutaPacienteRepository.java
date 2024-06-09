package com.decroly.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.decroly.example.model.TerapeutaPaciente;


public interface TerapeutaPacienteRepository extends JpaRepository<TerapeutaPaciente, Long>{
	List<TerapeutaPaciente> findByPacienteId(Long pacienteId);
}
