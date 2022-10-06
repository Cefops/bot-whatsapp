package br.com.cefops.SendMenssage.security.repository;

import br.com.cefops.SendMenssage.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}
