package br.com.springarchetype.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springarchetype.models.Usuario;
import br.com.springarchetype.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void gravar(Usuario usuario) {
		usuarioRepository.save(usuario);		
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
}
