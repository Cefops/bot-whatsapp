package br.com.cefops.SendMenssage.events.repository;

import br.com.cefops.SendMenssage.events.entity.CustomWpMessageEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomWpMessageEventEntityRepository extends JpaRepository<CustomWpMessageEventEntity, String> {
}