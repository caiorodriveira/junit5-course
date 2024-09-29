package br.sc.senai.domain.builder;

import br.sc.senai.domain.Usuario;


public class UsuarioBuilder {
	private Usuario usuario;
	private UsuarioBuilder(){}

	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		inicializarValoresPadroes(builder);
		return builder;
	}

	public static void inicializarValoresPadroes(UsuarioBuilder builder) {
		builder.usuario = new Usuario();
		Usuario usuario = builder.usuario;

		
		usuario.setId(1L);
		usuario.setNome("Usuário Válido");
		usuario.setLogin("usuario@usuario.com");
		usuario.setSenha("user@123");
	}

	public UsuarioBuilder comId(Long param) {
		usuario.setId(param);
		return this;
	}

	public UsuarioBuilder comNome(String param) {
		usuario.setNome(param);
		return this;
	}

	public UsuarioBuilder comLogin(String param) {
		usuario.setLogin(param);
		return this;
	}

	public UsuarioBuilder comSenha(String param) {
		usuario.setSenha(param);
		return this;
	}

	public Usuario build() {
		return usuario;
	}
}
