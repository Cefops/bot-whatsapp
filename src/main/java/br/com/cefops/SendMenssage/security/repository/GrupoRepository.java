package br.com.cefops.SendMenssage.security.repository;

import br.com.cefops.SendMenssage.security.model.Grupo;
import br.com.cefops.SendMenssage.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findByUsuariosIn(List<Usuario> usuario);

}
