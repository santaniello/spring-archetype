package br.com.springarchetype.repositories;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.springarchetype.confs.JpaTestConfiguration;
import br.com.springarchetype.models.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UsuarioRepository.class, JpaTestConfiguration.class})
@ActiveProfiles("test")
@Transactional
public class UsuarioTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Test
	public void  salvaUsuario(){
		Usuario u = new Usuario();
		u.setNome("Felipe Santaniello");
		u.setEmail("felipe_sbc_sp@hotmail.com");
		u.setSenha("f10121426");
		Usuario save = repository.save(u);
		System.out.println(save.getId());
		Assert.assertTrue("Sucesso", save.getId() > 0);
	}

}
