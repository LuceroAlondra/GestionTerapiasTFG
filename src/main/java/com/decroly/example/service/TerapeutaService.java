package com.decroly.example.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decroly.example.exception.ResourceNotFoundException;
import com.decroly.example.model.Paciente;
import com.decroly.example.model.Terapeuta;
import com.decroly.example.model.TerapeutaPaciente;
import com.decroly.example.repository.EspecialidadRepository;
import com.decroly.example.repository.PacienteRepository;
import com.decroly.example.repository.TerapeutaRepository;

@Service
public class TerapeutaService {
	
	private final TerapeutaRepository terapeutaRepository;
	private final EspecialidadRepository especialidadRepository;
	private final PacienteRepository pacienteRepository;
	
	@Autowired
	public TerapeutaService(TerapeutaRepository terapeutaRepository, EspecialidadRepository especialidadRepository, PacienteRepository pacienteRepository) {
	    this.terapeutaRepository = terapeutaRepository;
	    this.especialidadRepository = especialidadRepository;
	    this.pacienteRepository = pacienteRepository;
	}

	public Terapeuta crearTerapeuta(Terapeuta terapeuta) {
	    return terapeutaRepository.save(terapeuta);
	}

	public void agregarPaciente(Long terapeutaId, Long pacienteId) {
        Terapeuta terapeuta = terapeutaRepository.findById(terapeutaId)
                .orElseThrow(() -> new ResourceNotFoundException("Terapeuta no encontrado con el ID: " + terapeutaId));
        
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + pacienteId));

        TerapeutaPaciente terapeutaPaciente = new TerapeutaPaciente();
        terapeutaPaciente.setTerapeuta(terapeuta);
        terapeutaPaciente.setPaciente(paciente);
        terapeutaPaciente.setStartDate(LocalDate.now());
        terapeutaPaciente.setActual(true);

        terapeuta.getPacientesAtendidos().add(terapeutaPaciente);
        
        terapeutaRepository.save(terapeuta);
    }
}

