package br.com.cefops.SendMenssage.meta.whatsapp.repository;

import br.com.cefops.SendMenssage.meta.whatsapp.data.WpMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WpMessageEntityRepository extends JpaRepository<WpMessageEntity, Long> {
}