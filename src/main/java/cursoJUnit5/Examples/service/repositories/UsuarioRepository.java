package cursoJUnit5.Examples.service.repositories;

import java.util.Optional;

import cursoJUnit5.Examples.domain.Usuario;

public interface UsuarioRepository {
	
	Usuario salvar(Usuario usuario);
	
	Optional<Usuario> getUserByEmail(String email);
}
