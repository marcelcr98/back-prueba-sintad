package com.example.testsintad.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testsintad.models.Entidad;
import com.example.testsintad.repositories.EntidadRepo;
import com.example.testsintad.repositories.GenericRepo;
import com.example.testsintad.services.EntidadService;

@Service
public class EntidadServiceImpl extends CRUDImpl<Entidad, Integer> implements EntidadService {

	@Autowired
	private EntidadRepo repo;

	@Override
	protected GenericRepo<Entidad, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
}