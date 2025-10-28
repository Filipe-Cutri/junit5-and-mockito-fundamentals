package cursoJUnit5.Examples.service.repositories;

import java.util.List;

import cursoJUnit5.Examples.domain.Conta;

public interface ContaRepository {

	Conta salvar(Conta conta);
	
	List<Conta> obterContasPorUsuario(Long usuarioId);
	
	void delete(Conta conta);
}
