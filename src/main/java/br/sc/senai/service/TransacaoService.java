package br.sc.senai.service;

import br.sc.senai.domain.Transacao;
import br.sc.senai.exception.NotNullException;
import br.sc.senai.repository.TransacaoDao;

public class TransacaoService {
	
	private TransacaoDao transacaoDao;
	
	public Transacao salvar(Transacao transacao) {
		if(transacao.getDescricao() == null) throw new NotNullException("Descrição");
		if(transacao.getValor() == null) throw new NotNullException("Valor");
		if(transacao.getData() == null) throw new NotNullException("Data");
		if(transacao.getConta() == null) throw new NotNullException("Conta");
		if(transacao.getStatus() == null) transacao.setStatus(false);;
		
		return transacaoDao.salvar(transacao);
	}
}
