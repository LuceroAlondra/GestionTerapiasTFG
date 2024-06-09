package com.decroly.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.decroly.example.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Paciente findByEmail(String email);

}
