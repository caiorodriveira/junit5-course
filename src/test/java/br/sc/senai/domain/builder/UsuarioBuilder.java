package br.sc.senai.domain.builder;

import br.sc.senai.domain.Usuario;

public class UsuarioBuilder {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario () {
		UsuarioBuilder builder = new UsuarioBuilder();
		inicializarValoresPadroes(builder);
		return builder;
	}
	
	private static void inicializarValoresPadroes(UsuarioBuilder builder) {
		builder.id = 1L;
		builder.nome = "Usuário Válido";
		builder.login = "usuario@usuario.com";
		builder.senha = "user@123";
		
	}
	
	public UsuarioBuilder comId(Long param) {
		id = param;
		return this;
	}
	
	public UsuarioBuilder comNome(String param) {
		nome = param;
		return this;
	}
	
	public UsuarioBuilder comLogin(String param) {
		login = param;
		return this;
	}
	
	public UsuarioBuilder comSenha(String param) {
		senha = param;
		return this;
	}
	
	public Usuario build() {
		return new Usuario(id, nome, login, senha);
	}
	
}
