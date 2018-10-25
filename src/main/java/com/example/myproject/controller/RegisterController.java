package com.example.myproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myproject.model.Usuario;
import com.example.myproject.model.enums.TipoPessoa;
import com.example.myproject.repository.CategoriaRepository;
import com.example.myproject.service.UsuarioService;
import com.example.myproject.service.exception.EmailUsuarioCadastradoException;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/cadastrar-usuario");	
		mv.addObject("categorias",  categoriaRepository.findAll());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		return mv;
	}
	
	@PostMapping
	public ModelAndView register(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			usuarioService.salvar(usuario);
		}catch(EmailUsuarioCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		attributes.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/register");
	}
}
