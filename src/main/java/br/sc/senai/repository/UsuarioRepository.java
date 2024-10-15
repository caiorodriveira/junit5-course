package br.sc.senai.repository;

import java.util.Optional;

import br.sc.senai.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUsuarioByLogin(String login);

}
