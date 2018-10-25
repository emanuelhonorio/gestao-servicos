package com.example.myproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myproject.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	List<Categoria> findAllByNomeContaining(String nome);
}
