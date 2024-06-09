package com.decroly.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.decroly.example.model.Terapeuta;
import com.decroly.example.service.TerapeutaService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/terapeuta")
@CrossOrigin(origins = "*")
public class TerapeutaController {
	private final TerapeutaService terapeutaService;

    // Constructor
    public TerapeutaController(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }
    
    @PostMapping("/crear")
    public Terapeuta crearTerapeuta(@RequestBody Terapeuta terapeuta) {
        return terapeutaService.crearTerapeuta(terapeuta);
    }
    
    @Transactional
    @PostMapping("/agregarPaciente/{terapeutaId}/{pacienteId}")
    public void agregarPaciente(@PathVariable Long terapeutaId, @PathVariable Long pacienteId) {
        terapeutaService.agregarPaciente(terapeutaId, pacienteId);
    }
}
