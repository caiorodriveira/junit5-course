package br.sc.senai.service;

import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.sc.senai.domain.Usuario;
import br.sc.senai.repository.UsuarioRepository;

public class UsuarioServiceTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	@InjectMocks
	private UsuarioService usuarioService;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void deveRetornarEmptyQuandoUsuarioInexistente() {		
		/*
		 * quando chamar o método dentro do when (getUsuarioByLogin) retorne vazio
		 * Nesse caso é redundante pq a classe ja retorna um empty por padrão
		 */
		when(usuarioRepository.getUsuarioByLogin("vazio@vazio.com")).thenReturn(Optional.empty());
		
		//obedencendo regra acima
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("teste@teste.com");
		assertTrue(usuario.isEmpty());
	}
	
	@Test
	public void deveRetornarUsuarioPorEmail() {
		when(usuarioRepository.getUsuarioByLogin("usuario@usuario.com"))
		.thenReturn(Optional.of(umUsuario().build()));
		
		//obedencendo regra acima -- onde é inciado a execução do teste
		Optional<Usuario> usuario = usuarioService.getUsuarioByLogin("usuario@usuario.com");
		usuario = usuarioService.getUsuarioByLogin("usuario@usuario.com");
		assertTrue(usuario.isPresent());
		
		//verifica se o método foi chamado com esses parametros e 1 vez
		/*
		 * times(0) quantas vezes
		 * atLeastOnce() pelo menos 1 vez
		 * atLeast(0) pelo menos 0 vezes
		 * never() nunca ocorreu
		*/
		verify(usuarioRepository, times(2)).getUsuarioByLogin("usuario@usuario.com");
		
		/* verifica se não teve verificação nenhuma no atributo definido 
		 * isso depois de verificar se chamou, se comentar a verify de cima dara erro pq ele foi chamado
		 */
		verifyNoMoreInteractions(usuarioRepository);
		
		
	}
	
	@Test
	public void deveSalvarUsuarioComSucesso() {
		Usuario usuarioToSave = umUsuario().comId(null).build();
		
		//PRIMEIRO DEFINIR O QUE O MOCK DEVE FAZER QUANDO OS MÉTODOS SÃO CHAMADOS
		
		//desnecessário pois o padrão/correto é que retorne null
//		when(usuarioRepository.getUsuarioByLogin(usuarioToSave.getLogin()))
//				.thenReturn(Optional.empty());
		when(usuarioRepository.salvar(usuarioToSave)).thenReturn(umUsuario().build());
		
		
		// DEPOIS REALIZAR AS CHAMADAS DOS MÉTODOS (SIMULAR SALVAR)
		Usuario savedUsuario = usuarioService.salvar(usuarioToSave);
		assertNotNull(savedUsuario.getId());
		
		//nesse caso necessário pois esse método de verificação é chamado dentro de salvar
		verify(usuarioRepository).getUsuarioByLogin(usuarioToSave.getLogin());
		// não precisaria pois ja estou fazendo uma assertiva (not null)
//		verify(usuarioRepository).salvar(usuarioToSave);
		
	}
	
}
