package br.com.springarchetype.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuario")
@XmlRootElement(name = "usuario")
@XmlType(propOrder = {"id", "nome", "email", "senha"})
public class Usuario{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Este campo não pode ser nulo!")
	private String nome;
	
	@NotEmpty(message="Este campo não pode ser nulo!")
	private String email;
	private String senha;
		
	public Long getId() {
		return id;
	}

	@XmlElement
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	@XmlElement
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	@XmlElement
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
