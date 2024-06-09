package com.decroly.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decroly.example.model.Especialidad;
import com.decroly.example.repository.EspecialidadRepository;

@Service
public class EspecialidadService {

	@Autowired
    private EspecialidadRepository especialidadRepository;

    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

}
