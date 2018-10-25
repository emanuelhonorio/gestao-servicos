package com.example.myproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Categoria;
import com.example.myproject.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	List<Usuario> findByCategoria(Categoria categoria);
	
	Optional<Usuario> findByEmailIgnoreCase(String email);
}
