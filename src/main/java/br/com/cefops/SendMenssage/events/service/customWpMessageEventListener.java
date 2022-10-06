package br.com.cefops.SendMenssage.events.service;

import br.com.cefops.SendMenssage.events.entity.CustomWpMessageEventEntity;
import br.com.cefops.SendMenssage.events.event.CustomWpMessageEvent;
import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class customWpMessageEventListener  implements ApplicationListener<CustomWpMessageEvent> {
    @Autowired
    private CustomWpMessageEventService service;
    @Override
    public void onApplicationEvent(CustomWpMessageEvent event) {
        CustomWpMessageEventEntity entity=new CustomWpMessageEventEntity();

        if(service.getById(event.getMessage().getToken()) !=null){
            CustomWpMessageEventModel model=event.getMessage();

            entity.setToken(model.getToken());

            entity.setTotalSend(model.getTotalSend());
            entity.setStatus(model.getStatus());
            entity.setTotalMessage(model.getTotalMessage());
            entity.setLastNumber(model.getLastNumber());
            entity.setTotalError(model.getTotalError());
            entity.setUserRecive(model.getUserRecive());
            entity.setId(model.getToken());
            entity.setUserSend(model.getUserSend());
            entity.setFinalized(model.getFinalized());

            service.newEvent(entity);

        }else{
            CustomWpMessageEventModel model=event.getMessage();

            entity.setToken(model.getToken());
            entity.setTotalSend(model.getTotalSend());
            entity.setStatus(model.getStatus());
            entity.setTotalMessage(model.getTotalMessage());
            entity.setLastNumber(model.getLastNumber());
            entity.setTotalError(model.getTotalError());
            entity.setUserRecive(model.getUserRecive());
            entity.setId(model.getToken());
            entity.setFinalized(model.getFinalized());


            service.updateEvent(entity);
        }
        System.out.println(event.getMessage().getTotalError());

    }
}
