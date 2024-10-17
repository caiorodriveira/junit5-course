package br.sc.senai.external;

import br.sc.senai.domain.Conta;

public interface ContaEvent {
	
	public enum EventTyoe {CREATED, UPDATED, DELETED}
	
	void dispatch(Conta conta, EventTyoe type) throws Exception;

}
