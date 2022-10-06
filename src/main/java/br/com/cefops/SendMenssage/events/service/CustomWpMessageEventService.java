package br.com.cefops.SendMenssage.events.service;

import br.com.cefops.SendMenssage.events.entity.CustomWpMessageEventEntity;
import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import br.com.cefops.SendMenssage.events.repository.CustomWpMessageEventEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomWpMessageEventService {
    @Autowired
    private CustomWpMessageEventEntityRepository repository;

    public CustomWpMessageEventEntity getById(String id) {

        return repository.getReferenceById(id);
    }

    public CustomWpMessageEventModel newEvent(CustomWpMessageEventEntity data) {
        return repository.save(data);
    }

    public CustomWpMessageEventModel updateEvent(CustomWpMessageEventEntity data) {
        return repository.save(data);
    }



}
