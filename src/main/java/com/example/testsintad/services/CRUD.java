package com.example.testsintad.services;

import java.util.List;

public interface CRUD<T, V> {

	T registrar(T obj) throws Exception;
	T modificar(T obj) throws Exception;
	List<T> listar() throws Exception;
	T listarPorId(V id) throws Exception;
	void eliminar(V id) throws Exception;
}