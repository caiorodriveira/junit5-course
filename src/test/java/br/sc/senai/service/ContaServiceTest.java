package br.sc.senai.service;

import static br.sc.senai.domain.builder.ContaBuilder.umaConta;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.sc.senai.domain.Conta;
import br.sc.senai.exception.AlreadyExistsException;
import br.sc.senai.external.ContaEvent;
import br.sc.senai.external.ContaEvent.EventTyoe;
import br.sc.senai.repository.ContaRepository;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

	@Mock
	private ContaRepository contaRepository;
	@Mock
	private UsuarioService usuarioService;
	@Mock
	private ContaEvent contaEvent;
	@InjectMocks
	private ContaService contaService;
	
	
	@Test
	public void deveSalvarContaComSucesso() {
		Conta contaToSave = umaConta().comId(null).build();
		
		mockUsuarioExistente(contaToSave);
		when(contaRepository.salvar(contaToSave)).thenReturn(umaConta().build());
		doNothing().when(contaEvent).dispatch(umaConta().build(), EventTyoe.CREATED);
		
		Conta contaSalva = contaService.salvar(contaToSave);
		
		assertNotNull(contaSalva.getId());
		verify(usuarioService).getUsuarioById(contaToSave.getUsuario().getId());
	}
	
	@Test
	public void deveRejeitarContaComNomeExistentePorUsuario() {
		Conta contaToSave = umaConta().comId(null).comNome("Conta existente").build();

		mockUsuarioExistente(contaToSave);
		when(contaRepository.getContaByUsuarioAndNome(contaToSave.getUsuario(),contaToSave.getNome())).thenReturn(Optional.of(umaConta().build()));
		
		AlreadyExistsException ex = assertThrows(AlreadyExistsException.class, () -> contaService.salvar(contaToSave));
		
		assertTrue(ex.getMessage().endsWith(AlreadyExistsException.JA_EXISTE_MESSAGE));
		verify(contaRepository, never()).salvar(contaToSave);
	}
	
	private void mockUsuarioExistente(Conta contaToSave) {
		when(usuarioService.getUsuarioById(contaToSave.getUsuario().getId())).thenReturn(Optional.of(contaToSave.getUsuario()));
	}
	
}
