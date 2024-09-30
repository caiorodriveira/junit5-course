package br.sc.senai.domain;

import br.sc.senai.exceptions.NotNullException;

public class Conta {
	private Long id;
	private String nome;
	private Usuario usuario;
	
	public Conta() {}
	
	public Conta(Long id, String nome, Usuario usuario) {
		if(nome == null) throw new NotNullException("Usuário da conta");
		if(usuario == null) throw new NotNullException("Usuário da conta");
		
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome == null) throw new NotNullException("Nome da conta");
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		if(usuario == null) throw new NotNullException("Usuário da conta");
		this.usuario = usuario;
	}
	
	
	
	
}
