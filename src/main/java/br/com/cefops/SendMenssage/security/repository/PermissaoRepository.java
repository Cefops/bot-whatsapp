package br.com.cefops.SendMenssage.security.repository;


import br.com.cefops.SendMenssage.security.model.Grupo;
import br.com.cefops.SendMenssage.security.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
	
	List<Permissao> findByGruposIn(List<Grupo> grupo);

}
