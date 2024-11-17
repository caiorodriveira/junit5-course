package br.sc.senai.service;

import static br.sc.senai.domain.builder.ContaBuilder.umaConta;
import static br.sc.senai.domain.builder.TransacaoBuilder.umaTransacao;
import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.sc.senai.domain.Transacao;
import br.sc.senai.repository.TransacaoDao;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
	@InjectMocks
	private TransacaoService transacaoService;
	@Mock
	private TransacaoDao transacaoDao;
	
	@Test
	public void deveSalvarTransacaoValida() {
		Transacao transacaoToSave = umaTransacao().comId(null).build();
		when(transacaoDao.salvar(transacaoToSave)).thenReturn(umaTransacao().build());
		
		Transacao savedTransacao = transacaoService.salvar(transacaoToSave);
		assertNotNull(savedTransacao.getId());
		
		assertAll("Transação", 
				() -> assertEquals(umaTransacao().build().getId(), savedTransacao.getId()),
				() -> assertEquals(umaTransacao().build().getDescricao(), savedTransacao.getDescricao()), 
				() -> {
					assertAll("Conta", 
							() -> assertEquals(umaConta().build().getNome(),savedTransacao.getConta().getNome()),
							() -> {
								assertAll("Usuário", 
										() -> assertEquals(umUsuario().build().getNome(), savedTransacao.getConta().getUsuario().getNome()),
										() -> assertEquals(umUsuario().build().getSenha(), savedTransacao.getConta().getUsuario().getSenha())
								);
							}
					);
				}
		);
		
	}
	
}
