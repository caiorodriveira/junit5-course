package br.sc.senai.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;

import org.junit.jupiter.api.Test;

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
		
		assertEquals("Nome"+NotNullException.NOT_NULL_MESSAGE, ex.getMessage());
	}
	
	@Test
	public void deveRejeitarAlgumCampoVazio() {
		assertThrows(NotNullException.class, () -> umUsuario().comLogin(null).build());
	}

}
