package com.example.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myproject.model.Comentario;
import com.example.myproject.model.Usuario;
import com.example.myproject.repository.ComentarioRepository;
import com.example.myproject.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	
	@GetMapping("/{id}")
	public ModelAndView details(@PathVariable Long id) {
		
		ModelAndView mv = new ModelAndView("usuario/usuario-detalhes");
		Usuario usu = usuarioRepository.getOne(id);
		mv.addObject("usuario", usu);
		mv.addObject("usuarioLogado", usuarioRepository.getOne(1L));
		mv.addObject("comentario", new Comentario());
		mv.addObject("comentarios",comentarioRepository.findAllByDestinatario(usu));
		
		return mv;
		
	}
}
