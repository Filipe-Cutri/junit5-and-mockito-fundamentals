package cursoJUnit5.Examples.infra;

import static cursoJUnit5.Examples.domain.builders.UsuarioBuilder.umUsuario;

import java.util.Optional;

import cursoJUnit5.Examples.domain.Usuario;
import cursoJUnit5.Examples.service.repositories.UsuarioRepository;

public class UsuarioDummyRepository implements UsuarioRepository {

	@Override
	public Usuario salvar(Usuario usuario) {
		return umUsuario()
				.comNome(usuario.nome())
				.comEmail(usuario.email())
				.comSenha(usuario.senha())
			.agora();
	}

	@Override
	public Optional<Usuario> getUserByEmail(String email) {
		if("user@mail.com".equals(email)) 
			return Optional.of(umUsuario().comEmail(email).agora());
		return Optional.empty();
	}

}
