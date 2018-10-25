package com.example.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myproject.model.Usuario;
import com.example.myproject.repository.UsuarioRepository;
import com.example.myproject.service.exception.EmailUsuarioCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario usuario) {
		
		if(usuarioRepository.findByEmailIgnoreCase(usuario.getEmail()).isPresent()) {
			throw new EmailUsuarioCadastradoException("Esse e-mail já foi cadastrado");
		}
		
		return usuarioRepository.saveAndFlush(usuario);
	}
	
}
