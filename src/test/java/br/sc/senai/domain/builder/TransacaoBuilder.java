package br.sc.senai.domain.builder;

import static br.sc.senai.domain.builder.ContaBuilder.umaConta;

import java.time.LocalDateTime;

import br.sc.senai.domain.Conta;
import br.sc.senai.domain.Transacao;


public class TransacaoBuilder {
	private Transacao transacao;
	
	private TransacaoBuilder(){}

	public static TransacaoBuilder umaTransacao() {
		TransacaoBuilder builder = new TransacaoBuilder();
		inicializarValoresPadroes(builder);
		return builder;
	}

	public static void inicializarValoresPadroes(TransacaoBuilder builder) {
		builder.transacao = new Transacao();
		Transacao transacao = builder.transacao;

		
		transacao.setId(1L);
		transacao.setDescricao("Transação build");
		transacao.setValor(1.0);
		transacao.setConta(umaConta().build());
		transacao.setData(LocalDateTime.now());
		transacao.setStatus(false);
	}

	public TransacaoBuilder comId(Long param) {
		transacao.setId(param);
		return this;
	}

	public TransacaoBuilder comDescricao(String param) {
		transacao.setDescricao(param);
		return this;
	}

	public TransacaoBuilder comValor(Double param) {
		transacao.setValor(param);
		return this;
	}

	public TransacaoBuilder comConta(Conta param) {
		transacao.setConta(param);
		return this;
	}

	public TransacaoBuilder comData(LocalDateTime param) {
		transacao.setData(param);
		return this;
	}

	public TransacaoBuilder comStatus(Boolean param) {
		transacao.setStatus(param);
		return this;
	}

	public Transacao build() {
		return new Transacao(transacao);
	}
}