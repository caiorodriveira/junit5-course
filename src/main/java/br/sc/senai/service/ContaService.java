package br.sc.senai.service;

import br.sc.senai.domain.Conta;
import br.sc.senai.exceptions.AlreadyExistsException;
import br.sc.senai.exceptions.NotFoundException;
import br.sc.senai.repository.ContaRepository;

public class ContaService {

	private ContaRepository contaRepository;
	private UsuarioService usuarioService;
	
	public ContaService(ContaRepository contaRepository, UsuarioService usuarioService) {
		this.contaRepository = contaRepository;
		this.usuarioService = usuarioService;
	}

	
	public Conta salvar(Conta conta) {
		usuarioService.getUsuarioById(conta.getUsuario().getId()).orElseThrow(() -> 
			new NotFoundException("Usuário")
		);
	
		contaRepository.getContaByUsuarioAndNome(conta.getUsuario(), conta.getNome()).ifPresent(c -> {
			throw new AlreadyExistsException(String.format("Conta com para o usuário %s esse nome %s", c.getUsuario().getNome(), c.getNome()));
		});
		
		return contaRepository.salvar(conta);
		
	}
}
