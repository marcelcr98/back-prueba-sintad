package com.example.testsintad.services.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testsintad.models.Documento;
import com.example.testsintad.repositories.DocumentoRepo;
import com.example.testsintad.repositories.GenericRepo;
import com.example.testsintad.services.DocumentoService;

@Service
public class DocumentoServiceImpl extends CRUDImpl<Documento, Integer> implements DocumentoService {

	@Autowired
	private DocumentoRepo repo;

	@Override
	protected GenericRepo<Documento, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
}