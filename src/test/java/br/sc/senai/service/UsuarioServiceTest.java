package br.sc.senai.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.sc.senai.domain.Usuario;
import br.sc.senai.repository.UsuarioRepository;

public class UsuarioServiceTest {
	private UsuarioService usuarioService;
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
		usuarioService = new UsuarioService(usuarioRepository);
		
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("teste@teste.com");
		assertTrue(usuario.isEmpty());
	}
}
