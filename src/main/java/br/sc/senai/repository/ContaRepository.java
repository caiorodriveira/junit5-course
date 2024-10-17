package br.sc.senai.repository;

import java.util.List;
import java.util.Optional;

import br.sc.senai.domain.Conta;
import br.sc.senai.domain.Usuario;

public interface ContaRepository {

	Conta salvar(Conta conta);
	
	Optional<Conta> getContaByIdConta(Long idConta);
	
	List<Conta> getContasByUsuario(Usuario usuario);
	
	Optional<Conta> getContaByUsuarioAndNome(Usuario usuario, String  nome);
	
	void deleteContaById(Long id);
}
