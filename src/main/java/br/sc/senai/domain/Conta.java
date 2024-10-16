package br.sc.senai.domain;

import java.util.Objects;

import br.sc.senai.exception.NotNullException;

public class Conta {
	private Long id;
	private String nome;
	private Usuario usuario;
	
	public Conta() {}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nome, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(usuario, other.usuario);
	}

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
