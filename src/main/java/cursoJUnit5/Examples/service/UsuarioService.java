package cursoJUnit5.Examples.service;

import java.util.Optional;

import cursoJUnit5.Examples.domain.Usuario;
import cursoJUnit5.Examples.domain.exceptions.ValidationException;
import cursoJUnit5.Examples.service.repositories.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Usuario salvar(Usuario usuario) {
		repository.getUserByEmail(usuario.email()).ifPresent(user -> {
			throw new ValidationException(String.format("Usuário %s já cadastrado!", usuario.email()));
		});
		return repository.salvar(usuario);
	}

	public Optional<Usuario> getUserByEmail(String email) {
		return repository.getUserByEmail(email);
	}
}
