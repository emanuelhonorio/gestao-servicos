package com.example.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myproject.model.Comentario;
import com.example.myproject.model.Usuario;
import com.example.myproject.repository.ComentarioRepository;
import com.example.myproject.repository.UsuarioRepository;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@PostMapping("/{id}")
	public String comentar(Comentario comentario, @PathVariable Long id) {
		
		Usuario destinatario = usuarioRepository.getOne(id);
		Usuario remetente = usuarioRepository.getOne(1L);
		comentario.setDestinatario(destinatario);
		comentario.setRemetente(remetente);
		
		comentarioRepository.save(comentario);
		
		return "redirect:/usuarios/" + id;
	}
	

}
