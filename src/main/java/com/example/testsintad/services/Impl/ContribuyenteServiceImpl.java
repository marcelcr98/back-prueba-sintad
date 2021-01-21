package com.example.testsintad.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testsintad.models.Contribuyente;
import com.example.testsintad.repositories.ContribuyenteRepo;
import com.example.testsintad.repositories.GenericRepo;
import com.example.testsintad.services.ContribuyenteService;

@Service
public class ContribuyenteServiceImpl extends CRUDImpl<Contribuyente, Integer> implements ContribuyenteService {

	@Autowired
	private ContribuyenteRepo repo;

	@Override
	protected GenericRepo<Contribuyente, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
}