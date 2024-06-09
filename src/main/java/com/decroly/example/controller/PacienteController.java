package com.decroly.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.decroly.example.dto.LoginRequest;
import com.decroly.example.model.Paciente;
import com.decroly.example.model.Terapeuta;
import com.decroly.example.service.PacienteService;


@RestController
@CrossOrigin(origins = "*")
public class PacienteController {
	@Autowired
    private PacienteService pacienteService;
	
	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Long userId = pacienteService.autenticarPaciente(loginRequest.getEmail(), loginRequest.getPassword());
        if (userId != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Inicio de sesión fallido. Verifica tus credenciales.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
	
	@PostMapping("/crear")
    public Paciente crearPaciente(@RequestBody Paciente paciente) {
        return pacienteService.crearPaciente(paciente
        );
    }
	
	 @PutMapping("/editar/{id}")
	    public Paciente editarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
	        return pacienteService.editarPaciente(id, paciente);
	    }
	 
	 @DeleteMapping("/borrar/{id}")
	    public String eliminarPaciente(@PathVariable Long id) {
	        pacienteService.eliminarPaciente(id);
	        return "Paciente eliminado con ID: " + id;
	    }
	 
	 @GetMapping("/id/{id}")
	    public Paciente obtenerPacientePorId(@PathVariable Long id) {
	        return pacienteService.obtenerPacientePorId(id);
	    }
	 
	 @GetMapping("/todos")
	    public List<Paciente> obtenerTodosLosPacientes() {
	        return pacienteService.obtenerTodosLosPacientes();
	    }
	 @GetMapping("/paciente/{id}/terapeutas")
	    public ResponseEntity<List<Terapeuta>> obtenerTerapeutasPorPacienteId(@PathVariable Long id) {
	        List<Terapeuta> terapeutas = pacienteService.obtenerTerapeutasPorPacienteId(id);
	        return ResponseEntity.ok(terapeutas);
	    }
}
