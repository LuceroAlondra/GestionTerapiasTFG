package com.decroly.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decroly.example.exception.ResourceNotFoundException;
import com.decroly.example.model.Paciente;
import com.decroly.example.model.Terapeuta;
import com.decroly.example.repository.PacienteRepository;
import com.decroly.example.utils.EmailValidator;

@Service
public class PacienteService {
	@Autowired
    private PacienteRepository pacienteRepository;

	public Paciente crearPaciente(Paciente paciente) {
        if (!EmailValidator.validate(paciente.getEmail())) {
            throw new IllegalArgumentException("Dirección de correo electrónico no válida");
        }

        return pacienteRepository.save(paciente);
    }

	public Long autenticarPaciente(String email, String password) {
        Paciente paciente = pacienteRepository.findByEmail(email);
        if (paciente != null && paciente.getPassword().equals(password)) {
            return paciente.getId();
        }
        return null;
    }
	
	public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + id));
    }

    public Paciente editarPaciente(Long pacienteId, Paciente paciente) {
    	Paciente pacienteExistente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + pacienteId));

        // Actualizar los campos del paciente existente
        pacienteExistente.setNombre(paciente.getNombre());
        pacienteExistente.setApellido(paciente.getApellido());
        pacienteExistente.setEmail(paciente.getEmail());
        pacienteExistente.setPhone(paciente.getPhone());
        pacienteExistente.setPassword(paciente.getPassword());

        return pacienteRepository.save(pacienteExistente);
    }

    public void eliminarPaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + id));
        pacienteRepository.delete(paciente);
    }
    
    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public List<Terapeuta> obtenerTerapeutasPorPacienteId(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con el ID: " + pacienteId));
        return paciente.getTerapeutasAsignados().stream()
                .map(terapeutaPaciente -> terapeutaPaciente.getTerapeuta())
                .collect(Collectors.toList());
    }
}
