package com.example.testsintad.repositories;

import com.example.testsintad.models.Usuario;

public interface UsuarioRepo extends GenericRepo<Usuario, Integer>{

	Usuario findOneByUsername(String username);
}