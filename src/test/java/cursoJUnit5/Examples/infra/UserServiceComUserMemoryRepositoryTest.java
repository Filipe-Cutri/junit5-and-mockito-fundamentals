package cursoJUnit5.Examples.infra;

import static cursoJUnit5.Examples.domain.builders.UsuarioBuilder.umUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import cursoJUnit5.Examples.domain.Usuario;
import cursoJUnit5.Examples.domain.exceptions.ValidationException;
import cursoJUnit5.Examples.service.UsuarioService;

@Tag("infra")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {
	private static UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());


	@Test
	void testeBasico() {
		System.out.println("Executou teste!");
	}

	
	@Test
	@Order(1)
	public void deveSalvarUsuarioValido() {
		Usuario user = service.salvar(umUsuario().comId(null).agora());
		Assertions.assertNotNull(user.id());
	}

	@Test
	@Order(2)
	public void deveRejeitarUsuarioExistente() {
		ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> 
			service.salvar(umUsuario().comId(null).agora()));
		Assertions.assertEquals("Usuário user@mail.com já cadastrado!", ex.getMessage());
	}
}
