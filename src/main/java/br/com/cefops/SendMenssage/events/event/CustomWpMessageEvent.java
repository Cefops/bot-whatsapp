package br.com.cefops.SendMenssage.events.event;

import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class CustomWpMessageEvent  extends ApplicationEvent {
    private CustomWpMessageEventModel message;

    public CustomWpMessageEvent(Object source,CustomWpMessageEventModel message) {
        super(source);
        this.message=message;
    }

    public CustomWpMessageEvent(Object source, Clock clock) {
        super(source, clock);
    }
    public  CustomWpMessageEventModel getMessage(){
        return message;
    }
}
