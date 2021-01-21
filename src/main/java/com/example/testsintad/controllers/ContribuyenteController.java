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

import com.example.testsintad.models.Contribuyente;
import com.example.testsintad.services.ContribuyenteService;
import com.example.testsintad.exception.ModeloNotFoundException;

@RestController
@RequestMapping("/contribuyentes")
public class ContribuyenteController {

	@Autowired
	private ContribuyenteService service;

	@GetMapping
	public ResponseEntity<List<Contribuyente>> listar() throws Exception {
		List<Contribuyente> lista = service.listar();
		return new ResponseEntity<List<Contribuyente>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contribuyente> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Contribuyente obj = service.listarPorId(id);
		if (obj == null) {
			throw new ModeloNotFoundException("No se encontro el ID: " + id);
		}
		return new ResponseEntity<Contribuyente>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Contribuyente contribuyente) throws Exception {
		Contribuyente obj = service.registrar(contribuyente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdTipoContribuyente()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Contribuyente> modificar(@Valid @RequestBody Contribuyente contribuyente) throws Exception {
		Contribuyente obj = service.modificar(contribuyente);
		return new ResponseEntity<Contribuyente>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Contribuyente obj = service.listarPorId(id);
		if (obj.getIdTipoContribuyente() == null) {
			throw new ModeloNotFoundException("No se encontró el ID " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}