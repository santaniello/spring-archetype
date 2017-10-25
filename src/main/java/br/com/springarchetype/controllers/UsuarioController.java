package br.com.springarchetype.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springarchetype.models.Usuario;
import br.com.springarchetype.models.UsuarioWrapper;
import br.com.springarchetype.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {	
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
		
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}
	
	@CacheEvict(value="usuarioListar",allEntries=true)
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(usuario);
		}		
		usuarioService.gravar(usuario);
		redirectAttributes.addFlashAttribute("sucesso", "Usuario cadastrado com sucesso!");
		return new ModelAndView("redirect:/usuarios/form");
	}
	
	@Cacheable(value="usuarioListar") 
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		if(requestURI.contains("json") || requestURI.contains("xml")){
			ModelAndView mv = new ModelAndView();
			mv.addObject("usuario", new UsuarioWrapper(usuarioService.findAll()));	
			return mv;
		}
		throw new RuntimeException("Erro 404");
	}	
}
