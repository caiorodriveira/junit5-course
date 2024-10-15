package br.sc.senai.service;

import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.sc.senai.domain.Usuario;
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
		
		when(usuarioRepository.getUsuarioByLogin("usuario@usuario.com"))
		.thenReturn(Optional.of(umUsuario().build()));
		
		
		//obedencendo regra acima -- onde é inciado a execução do teste
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("usuario@usuario.com");
		assertTrue(usuario.isPresent());
		
		
		//verifica se o método foi chamado com esses parametros e 1 vez
		/*
		 * times(0) quantas vezes
		 * atLeastOnce() pelo menos 1 vez
		 * atLeast(0) pelo menos 0 vezes
		 * never() nunca ocorreu
		*/
		verify(usuarioRepository, times(1)).getUsuarioByLogin("usuario@usuario.com");
		
		/* verifica se não teve interação nenhuma no atributo definido 
		 * isso depois de verificar se chamou, se comentar a verify de cima dara erro pq ele foi chamado
		 */
		verifyNoInteractions(usuarioRepository);
	}
}
