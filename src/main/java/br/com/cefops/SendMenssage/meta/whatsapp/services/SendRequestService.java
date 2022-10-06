package br.com.cefops.SendMenssage.meta.whatsapp.services;

import br.com.cefops.SendMenssage.meta.whatsapp.model.*;

import br.com.cefops.SendMenssage.meta.whatsapp.request.WhatsappProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendRequestService {

    private WhatsappProxy sendMessage;

    @Autowired
    public SendRequestService(WhatsappProxy sendMessage) {
        this.sendMessage = sendMessage;
    }
    @Async
    public void sendNewMenssage(List<WpMessageModel> messageModelList, String id,String fileName) {
            var i=0;
            List<Integer> error=new ArrayList<>();
       for (WpMessageModel messageModel:messageModelList){

           WpSendMessageModel model = new WpSendMessageModel();
           WpTemplateModel teamplate = new WpTemplateModel();
           WpLanguageModel leanguage = new WpLanguageModel();

           leanguage.setCode("pt_BR");
           leanguage.setPolicy("deterministic");

           WpParameterModel nome = new WpParameterModel();
           nome.setType("text");
           nome.setText(messageModel.getName());

           WpParameterModel data = new WpParameterModel();
           data.setType("text");
           data.setText(messageModel.getDate());

           WpParameterModel valor = new WpParameterModel();
           valor.setType("text");
           valor.setText(messageModel.getValue());


           List<WpParameterModel> allparameter = new ArrayList<WpParameterModel>();
           allparameter.add(nome);
           allparameter.add(data);
           allparameter.add(valor);


           WpComponentModel component = new WpComponentModel();
           component.setType("body");
           component.setParameters(allparameter);
           List<WpComponentModel> allComponent = new ArrayList<WpComponentModel>();

           allComponent.add(component);

           teamplate.setName("cobrana");
           teamplate.setLanguage(leanguage);
           teamplate.setComponents(allComponent);


           model.setMessagingProduct("whatsapp");
           model.setTo("55"+messageModel.getPhone());
           model.setType("template");
           model.setTemplate(teamplate);

           i++;
           ResponseEntity<?> send=sendMessage.sendMessage(model,id,messageModelList.size(),i,error.size(),fileName);
           if (send.getStatusCode() == HttpStatus.BAD_REQUEST){
               error.add(i);
           }

       }


    }


}
