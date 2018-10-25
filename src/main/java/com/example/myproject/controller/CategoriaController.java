package com.example.myproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.Categoria;
import com.example.myproject.repository.CategoriaRepository;
import com.example.myproject.repository.UsuarioRepository;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ModelAndView search(Categoria categoria, BindingResult result) {
		
		ModelAndView mv = new ModelAndView("categoria/categorias");
		List<Categoria> categorias = null;
		
		if(StringUtils.isEmpty(categoria.getNome())) {
			categorias = categoriaRepository.findAll();
		} else {
			categorias = categoriaRepository.findAllByNomeContaining(categoria.getNome());
		}
		
		mv.addObject("categorias", categorias);
		
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView details(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.getOne(id);
		
		ModelAndView modelAndView = new ModelAndView("categoria/categoria-trabalhadores");
		modelAndView.addObject("categoriaNome", categoria.getNome());
		modelAndView.addObject("usuarios", usuarioRepository.findByCategoria(categoria));
		
		return modelAndView;
	}
	
}
