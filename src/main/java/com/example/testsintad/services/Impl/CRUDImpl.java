package com.example.testsintad.services.Impl;

import java.util.List;

import com.example.testsintad.repositories.GenericRepo;
import com.example.testsintad.services.CRUD;

public abstract class CRUDImpl<T,ID> implements CRUD<T, ID> {

	protected abstract GenericRepo<T, ID> getRepo();
	
	
	public T registrar(T obj) throws Exception {
		return getRepo().save(obj);
	}


	public T modificar(T obj) throws Exception {
		return getRepo().save(obj);
	}


	public List<T> listar() throws Exception {
		return getRepo().findAll();
	}
	

	public T listarPorId(ID id) throws Exception {
		return getRepo().findById(id).orElse(null);		
	}

	public void eliminar(ID id) throws Exception {
		getRepo().deleteById(id);
	}
}
