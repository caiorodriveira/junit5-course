package br.sc.senai.service;

import static br.sc.senai.domain.builder.ContaBuilder.umaConta;
import static br.sc.senai.domain.builder.TransacaoBuilder.umaTransacao;
import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.sc.senai.domain.Conta;
import br.sc.senai.domain.Transacao;
import br.sc.senai.exception.NotNullException;
import br.sc.senai.repository.TransacaoDao;

//Para alguma condição especifica, roda os testes unitário
//@EnabledIf(value = "isHoraValida")
@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
	@InjectMocks
	private TransacaoService transacaoService;
	@Mock
	private TransacaoDao transacaoDao;
	
	//dentro de um método assume a hopotese que nessa condição ele vai pular a execução do método
	//Assemptions.assumeTrue(LocaDateTime.now().getHour() < 18);
	
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
	
	@ParameterizedTest(name = "{6} - {1}")
	@MethodSource(value = "cenariosObrigatorios")
	public void deveValidarCamposObrigatoriosAoSalvar(Long id, String descricao, Double valor, LocalDateTime data, Conta conta, Boolean status, String errorMessage) {
		Transacao transacaoToSave = umaTransacao()
										.comId(id)
										.comDescricao(descricao)
										.comValor(valor)
										.comData(data)
										.comConta(conta)
										.comStatus(status)
									.build();
		
		String exMessage = assertThrows(NotNullException.class,  () -> transacaoService.salvar(transacaoToSave)).getMessage();
		
		assertEquals(errorMessage, exMessage);
		
	}
	
	static Stream<Arguments> cenariosObrigatorios() {
		return Stream.of(
					Arguments.of(1L, null, 1D, LocalDateTime.now(), umaConta().build(), false, "Descrição"+NotNullException.NOT_NULL_MESSAGE),
					Arguments.of(1L, "Transação Válida", null, LocalDateTime.now(), umaConta().build(), false, "Valor"+NotNullException.NOT_NULL_MESSAGE),
					Arguments.of(1L, "Transação Válida", 1D, null, umaConta().build(), false, "Data"+NotNullException.NOT_NULL_MESSAGE),
					Arguments.of(1L, "Transação Válida", 1D, LocalDateTime.now(), null, false, "Conta"+NotNullException.NOT_NULL_MESSAGE)
				);
	}
}
