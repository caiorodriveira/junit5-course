package br.sc.senai.service;

import java.util.Optional;

import br.sc.senai.domain.Usuario;
import br.sc.senai.exceptions.AlreadyExistsException;
import br.sc.senai.repository.UsuarioRepository;

public class UsuarioService {
	
	
	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.getUsuarioByLogin(usuario.getLogin()).ifPresent(u -> {
			throw new AlreadyExistsException(String.format("Usuario com email %s" + AlreadyExistsException.JA_EXISTE_MESSAGE, u.getLogin()));
		});
		
		return usuarioRepository.salvar(usuario);
	}
	
	public Optional<Usuario> getUsuarioByLogin(String login) {
		return usuarioRepository.getUsuarioByLogin(login);
	}
}