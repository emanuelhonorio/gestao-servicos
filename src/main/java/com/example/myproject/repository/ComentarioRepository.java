package com.example.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Comentario;
import com.example.myproject.model.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
	List<Comentario> findAllByDestinatario(Usuario usuario);

}
