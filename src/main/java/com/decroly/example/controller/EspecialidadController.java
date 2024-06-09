package com.decroly.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decroly.example.model.Especialidad;
import com.decroly.example.service.EspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
	@Autowired
    private EspecialidadService especialidadService;

	 @PostMapping("/crear")
	    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody Especialidad especialidad) {
	        Especialidad nuevaEspecialidad = especialidadService.crearEspecialidad(especialidad);
	        return ResponseEntity.ok(nuevaEspecialidad);
	    }
}
