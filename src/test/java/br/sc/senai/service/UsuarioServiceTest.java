package br.sc.senai.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.sc.senai.domain.Usuario;
import br.sc.senai.domain.builder.UsuarioBuilder;
import br.sc.senai.repository.UsuarioRepository;

public class UsuarioServiceTest {
	private UsuarioService usuarioService;
	
	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {
		UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
		usuarioService = new UsuarioService(usuarioRepository);
		
		/*
		 * quando chamar o método dentro do when (getUsuarioByLogin) retorne vazio
		 * Nesse caso é redundante pq a classe ja retorna um empty por padrão
		 */
		Mockito.when(usuarioRepository.getUsuarioByLogin("vazio@vazio.com")).thenReturn(Optional.empty());
		
		//obedencendo regra acima
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("teste@teste.com");
		assertTrue(usuario.isEmpty());
	}
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
		usuarioService = new UsuarioService(usuarioRepository);
		
		Mockito.when(usuarioRepository.getUsuarioByLogin("usuario@usuario.com"))
		.thenReturn(Optional.of(UsuarioBuilder.umUsuario().build()));
		
		//obedencendo regra acima
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("usuario@usuario.com");
		assertFalse(usuario.isEmpty());
	}
}
