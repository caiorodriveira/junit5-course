package br.sc.senai.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.sc.senai.exceptions.NotNullException;

public class UsuarioTest {
	
	@Test
	public void deveCriarUsuarioValido() {
		Usuario usuario = new Usuario(1L, "Usuário Valido", "usuario@usuario.com",  "user123");
		assertAll("Usuario",  
				() -> assertEquals(1l, usuario.getId()),
				() -> assertEquals("Usuário Valido", usuario.getNome()),
				() -> assertEquals("usuario@usuario.com", usuario.getLogin()),
				() -> assertEquals("user123", usuario.getSenha())
		);
		
	}	
	
	@Test
	public void deveRejeitarNomeVazio() {
		NotNullException ex = assertThrows(NotNullException.class, () -> new Usuario(1L, null, "email@email.com", "nome123"));
		
		assertEquals("Nome"+NotNullException.NOT_NULL_MESSAGE, ex.getMessage());
	}
	
	@Test
	public void deveRejeitarAlgumCampoVazio() {
		assertThrows(NotNullException.class, () -> new Usuario(1L, "nome", "email@email.com", null));
	}

}
