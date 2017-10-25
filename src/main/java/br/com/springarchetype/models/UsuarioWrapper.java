package br.com.springarchetype.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usuarios")
public class UsuarioWrapper{
	
	private List<Usuario> usuarios = null;

	public UsuarioWrapper() {}
	
	public UsuarioWrapper(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@XmlElement(name = "usuario")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}	
	
}
