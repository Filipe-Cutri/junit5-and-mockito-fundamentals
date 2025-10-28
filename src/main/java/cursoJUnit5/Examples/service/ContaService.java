package cursoJUnit5.Examples.service;

import java.time.LocalDateTime;
import java.util.List;

import cursoJUnit5.Examples.domain.Conta;
import cursoJUnit5.Examples.domain.exceptions.ValidationException;
import cursoJUnit5.Examples.service.external.ContaEvent;
import cursoJUnit5.Examples.service.external.ContaEvent.EventType;
import cursoJUnit5.Examples.service.repositories.ContaRepository;

public class ContaService {
	private ContaRepository repository;
	private ContaEvent event;

	public ContaService(ContaRepository repository, ContaEvent event) {
		this.repository = repository;
		this.event = event;
	}
	
	public Conta salvar(Conta conta) {
		List<Conta> contas = repository.obterContasPorUsuario(conta.usuario().id());
		contas.stream().forEach(contaExistente -> {
			if(conta.nome().equalsIgnoreCase(contaExistente.nome()))
				throw new ValidationException("Usuário já possui uma conta com este nome");
		});
		Conta novaConta = new Conta(conta.id(), conta.nome() + LocalDateTime.now(), conta.usuario());
		System.out.println(novaConta);
		Conta contaPersistida = repository.salvar(novaConta);
		try {
			event.dispatch(contaPersistida, EventType.CREATED);
		} catch (Exception e) {
			repository.delete(contaPersistida);
			throw new RuntimeException("Falha na criação da conta, tente novamente");
		}
		return contaPersistida;
	}
}
