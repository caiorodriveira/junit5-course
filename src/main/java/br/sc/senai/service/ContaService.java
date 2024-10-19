package br.sc.senai.service;

import java.time.LocalDateTime;

import br.sc.senai.domain.Conta;
import br.sc.senai.exception.AlreadyExistsException;
import br.sc.senai.exception.NotFoundException;
import br.sc.senai.external.ContaEvent;
import br.sc.senai.external.ContaEvent.EventTyoe;
import br.sc.senai.repository.ContaRepository;

public class ContaService {

	private ContaRepository contaRepository;
	private UsuarioService usuarioService;
	private ContaEvent contaEvent;
	
	public ContaService(ContaRepository contaRepository, UsuarioService usuarioService, ContaEvent contaEvent) {
		this.contaRepository = contaRepository;
		this.usuarioService = usuarioService;
		this.contaEvent = contaEvent;
	}

	
	public Conta salvar(Conta conta) {
		usuarioService.getUsuarioById(conta.getUsuario().getId()).orElseThrow(() -> 
			new NotFoundException("Usuário")
		);
	
		contaRepository.getContaByUsuarioAndNome(conta.getUsuario(), conta.getNome()).ifPresent(c -> {
			throw new AlreadyExistsException(String.format("Conta com para o usuário %s esse nome %s", c.getUsuario().getNome(), c.getNome()));
		});
		
		Conta contaPersisted = contaRepository.salvar(
				new Conta(conta.getId(), conta.getNome() + LocalDateTime.now(), conta.getUsuario()));
		try {
			contaEvent.dispatch(contaPersisted, EventTyoe.CREATED);
		} catch (Exception e) {
			contaRepository.deleteContaById(contaPersisted.getId());
			throw new RuntimeException("Erro ao salvar conta");
		}
		
		return contaPersisted;
		
	}
}
