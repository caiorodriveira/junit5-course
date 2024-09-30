package br.sc.senai.domain.builder;

import static br.sc.senai.domain.builder.UsuarioBuilder.umUsuario;

import br.sc.senai.domain.Conta;
import br.sc.senai.domain.Usuario;


public class ContaBuilder {
	private Conta conta;
	private ContaBuilder(){}

	public static ContaBuilder umaConta() {
		ContaBuilder builder = new ContaBuilder();
		inicializarValoresPadroes(builder);
		return builder;
	}

	public static void inicializarValoresPadroes(ContaBuilder builder) {
		builder.conta = new Conta();
		Conta conta = builder.conta;

		
		conta.setId(1L);
		conta.setNome("Conta Válida");
		conta.setUsuario(umUsuario().build());
	}

	public ContaBuilder comId(Long param) {
		conta.setId(param);
		return this;
	}

	public ContaBuilder comNome(String param) {
		conta.setNome(param);
		return this;
	}

	public ContaBuilder comUsuario(Usuario param) {
		conta.setUsuario(param);
		return this;
	}

	public Conta build() {
		return conta;
	}
}