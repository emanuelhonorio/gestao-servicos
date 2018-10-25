package com.example.myproject.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.example.myproject.model.Usuario;

public class UsuarioGroupSequenceProvider implements DefaultGroupSequenceProvider<Usuario> {

	@Override
	public List<Class<?>> getValidationGroups(Usuario usuario) {
		List<Class<?>> grupos = new ArrayList<>();
		grupos.add(Usuario.class);
		
		if(isUsuarioSelecionado(usuario)) {
			grupos.add(usuario.getTipoPessoa().getGrupo());
		}
		
		return grupos;
	}

	private boolean isUsuarioSelecionado(Usuario usuario) {
		return usuario != null && usuario.getTipoPessoa() != null;
	}
	
}
