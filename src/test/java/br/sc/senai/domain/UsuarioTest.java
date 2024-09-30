package br.sc.senai.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import br.sc.senai.exceptions.NotNullException;

public class UsuarioTest {
	
	@Test
	public void deveCriarUsuarioValido() {
		Usuario usuario = umUsuario().build();
		assertAll("Usuario",  
				() -> assertEquals(1l, usuario.getId()),
				() -> assertEquals("Usuário Válido", usuario.getNome()),
				() -> assertEquals("usuario@usuario.com", usuario.getLogin()),
				() -> assertEquals("user@123", usuario.getSenha())
		);
		
	}
	
	@Test
	public void deveRejeitarNomeVazio() {
		NotNullException ex = assertThrows(NotNullException.class, () -> umUsuario().comNome(null).build());
		
		assertEquals("Nome do usuário"+NotNullException.NOT_NULL_MESSAGE, ex.getMessage());
	}
	
	@Test
	public void deveValidarCamposObrigatorios() {
		assertThrows(NotNullException.class, () -> umUsuario().comValoresNulos().build());
	}
	
	/*
	 * Método que faz a validação a partir do que foi parametrizado (3 casos)
	 * nullValues deifine uma string para ser considerado um valor nulo
	 * name o nome que vai ser definido o teste (no exemplo é de acordo com o parametro)
	 * 
	*/
	@ParameterizedTest(name = "[{index}] - {4}")
	@CsvSource(value = {
			"1, NULL, usuario, u123, Nome do usuário não pode ser nulo",
			"1, Nome usuário, NULL, u123, Login do usuário não pode ser nulo",
			"1, Nome ususario, usuario, NULL, Senha do usuário não pode ser nulo"
	}, nullValues = "NULL")
	public void deveValidarAlgumCampopVazio(Long id, String nome, String login, String senha, String mensagem) {
		NotNullException ex = assertThrows(NotNullException.class, () -> umUsuario().comId(id).comLogin(login).comSenha(senha).comNome(nome).build());
		assertEquals(mensagem, ex.getMessage());
	}
}


