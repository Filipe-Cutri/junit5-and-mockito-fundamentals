package cursoJUnit5.Examples.service;

import static cursoJUnit5.Examples.domain.builders.ContaBuilder.umaConta;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import cursoJUnit5.Examples.domain.Conta;
import cursoJUnit5.Examples.domain.exceptions.ValidationException;
import cursoJUnit5.Examples.service.external.ContaEvent;
import cursoJUnit5.Examples.service.external.ContaEvent.EventType;
import cursoJUnit5.Examples.service.repositories.ContaRepository;

@Tag("service")
@Tag("conta")
@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
	@InjectMocks private ContaService service;
	@Mock private ContaRepository repository;
	@Mock private ContaEvent event;
	
	@Captor private ArgumentCaptor<Conta> contaCaptor;

	@Test
	public void deveSalvarPrimeiraContaComSucesso() throws Exception {
		Conta contaToSave = umaConta().comId(null).agora();
		when(repository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
		Mockito.doNothing().when(event).dispatch(umaConta().agora(), EventType.CREATED);
		
		Conta savedConta = service.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
		
		Mockito.verify(repository).salvar(contaCaptor.capture());
		Assertions.assertNull(contaCaptor.getValue().id());
		Assertions.assertTrue(contaCaptor.getValue().nome().startsWith("Conta Válida"));
	}
	
	@Test
	public void deveSalvarSegundaContaComSucesso() {
		Conta contaToSave = umaConta().comId(null).agora();
		when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
			.thenReturn(Arrays.asList(umaConta().comNome("Outra conta").agora()));
		when(repository.salvar(Mockito.any(Conta.class))).thenReturn(umaConta().agora());
		
		Conta savedConta = service.salvar(contaToSave);
		Assertions.assertNotNull(savedConta.id());
	}
	
	@Test
	public void deveRejeitarContaRepetida() {
		Conta contaToSave = umaConta().comId(null).agora();
		when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
			.thenReturn(Arrays.asList(umaConta().agora()));
		
		String mensagem = Assertions.assertThrows(ValidationException.class, () -> 
			service.salvar(contaToSave)).getMessage();
		Assertions.assertEquals("Usuário já possui uma conta com este nome", mensagem);
	}

	@Test
	public void naoDeveManterContaSemEvento() throws Exception {
		Conta contaToSave = umaConta().comId(null).agora();
		Conta contaSalva = umaConta().agora();
		when(repository.salvar(Mockito.any(Conta.class))).thenReturn(contaSalva);
		Mockito.doThrow(new Exception("Falha catrastrófica"))
			.when(event).dispatch(contaSalva, EventType.CREATED);
		
		String mensagem = Assertions.assertThrows(Exception.class, () -> 
			service.salvar(contaToSave)).getMessage();
		Assertions.assertEquals("Falha na criação da conta, tente novamente", mensagem);
		
		Mockito.verify(repository).delete(contaSalva);
	}
}
