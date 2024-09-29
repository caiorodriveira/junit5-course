package br.sc.senai.domain;

import br.sc.senai.exceptions.NotNullException;

public class Usuario {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	
	public Usuario(Long id, String nome, String login, String senha) {
		
		if(nome == null) throw new NotNullException("Nome");
		if(login == null) throw new NotNullException("Login");
		if(senha == null) throw new NotNullException("Senha");
		
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;	
	}

	public String getSenha() {
		return senha;
	}
	
	

	
}
