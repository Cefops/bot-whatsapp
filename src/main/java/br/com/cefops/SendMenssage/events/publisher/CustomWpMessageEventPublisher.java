package br.com.cefops.SendMenssage.events.publisher;

import br.com.cefops.SendMenssage.events.event.CustomWpMessageEvent;
import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomWpMessageEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomWpMessageEvent(final CustomWpMessageEventModel message) {
        CustomWpMessageEvent customWpMessageEvent=new CustomWpMessageEvent(this,message);
        applicationEventPublisher.publishEvent(customWpMessageEvent);
    }
}
