package com.example.testsintad.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.testsintad.services.EntidadService;
import com.example.testsintad.exception.ModeloNotFoundException;
import com.example.testsintad.models.Entidad;

@RestController
@RequestMapping("/entidades")
public class EntidadController {

	@Autowired
	private EntidadService service;
	
	@GetMapping
	public ResponseEntity<List<Entidad>> listar() throws Exception{
		List<Entidad> lista = service.listar();
		return new ResponseEntity<List<Entidad>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entidad> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Entidad obj = service.listarPorId(id);
		if(obj == null ) {
			throw new ModeloNotFoundException("No se encontro el ID: " + id);
		}
		return new ResponseEntity<Entidad>(obj, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Entidad entidad) throws Exception{
		Entidad obj = service.registrar(entidad);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdEntidad()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Entidad> modificar(@Valid @RequestBody Entidad paciente) throws Exception{
		Entidad obj = service.modificar(paciente);
		return new ResponseEntity<Entidad>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Entidad obj = service.listarPorId(id);
		if(obj.getIdEntidad() == null ) {
			throw new ModeloNotFoundException("No se encontro el ID" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}