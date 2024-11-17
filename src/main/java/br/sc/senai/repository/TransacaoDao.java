package br.sc.senai.repository;

import br.sc.senai.domain.Transacao;

public interface TransacaoDao {
	
	Transacao salvar(Transacao transacao);
}
