package br.sc.senai.domain;

import static br.sc.senai.domain.builder.ContaBuilder.umaConta;
import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import br.sc.senai.exception.NotNullException;

public class ContaTest {

	@Test
	public void deveCriarUmaContaValida(){
		Conta conta = umaConta().build();
		
		assertAll("Conta",
				() -> assertEquals(1L, conta.getId()),
				() -> assertEquals("Conta Válida", conta.getNome()),
				() -> assertEquals(umUsuario().build(), conta.getUsuario())
		);
	}	
	
	@ParameterizedTest(name = "[{index}] - {4}")
	@MethodSource(value = "contasInvalidas")
	public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String message) {
		String errorMessage = assertThrows(NotNullException.class, () -> {
			umaConta().comId(id).comNome(nome).comUsuario(usuario).build();
		}).getMessage();
		
	assertEquals(message, errorMessage);	
	}
	
	public static Stream<Arguments> contasInvalidas (){
		return
				Stream.of(
							Arguments.of(1L, null, umUsuario().build(), "Nome da conta não pode ser nulo"),
							Arguments.of(1L, "Conta Válida", null, "Usuário da conta não pode ser nulo")
						);
	}
	
}
