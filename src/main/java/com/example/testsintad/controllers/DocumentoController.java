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

import com.example.testsintad.services.DocumentoService;
import com.example.testsintad.exception.ModeloNotFoundException;
import com.example.testsintad.models.Documento;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

	@Autowired
	private DocumentoService service;
	
	@GetMapping
	public ResponseEntity<List<Documento>> listar() throws Exception{
		List<Documento> lista = service.listar();
		return new ResponseEntity<List<Documento>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Documento> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Documento obj = service.listarPorId(id);
		if(obj == null ) {
			throw new ModeloNotFoundException("No se encontro el ID: " + id);
		}
		return new ResponseEntity<Documento>(obj, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Documento documentos) throws Exception{
		Documento obj = service.registrar(documentos);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdTipoDocumento()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Documento> modificar(@Valid @RequestBody Documento documentos) throws Exception{
		Documento obj = service.modificar(documentos);
		return new ResponseEntity<Documento>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Documento obj = service.listarPorId(id);
		if(obj.getIdTipoDocumento() == null ) {
			throw new ModeloNotFoundException("No se encontro el ID: " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}