package br.com.cefops.SendMenssage.meta.whatsapp.request;

import br.com.cefops.SendMenssage.events.model.CustomWpMessageEventModel;
import br.com.cefops.SendMenssage.events.publisher.CustomWpMessageEventPublisher;
import br.com.cefops.SendMenssage.meta.whatsapp.data.WpMessageEntity;
import br.com.cefops.SendMenssage.meta.whatsapp.model.WpSendMessageModel;
import br.com.cefops.SendMenssage.meta.whatsapp.model.WpSendMessageResponseModel;
import br.com.cefops.SendMenssage.meta.whatsapp.services.SaveResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class WhatsappProxy {
    @Autowired
    private SaveResponseService service;
    @Autowired
    private CustomWpMessageEventPublisher publisher;
    CustomWpMessageEventModel eventMessage = new CustomWpMessageEventModel();

    public ResponseEntity<?> sendMessage(WpSendMessageModel model, String id, int totalMessage, int totalSend, int error,String fileName) {

        boolean finalized=totalMessage ==totalSend?true:false;


        error=   error==0?1:error;
        String name = model.getTemplate().getComponents().get(0).getParameters().get(0).getText();

        eventMessage.setTotalMessage(totalMessage);
        eventMessage.setUserSend("Emison");
        eventMessage.setTotalSend(totalSend);
        eventMessage.setTotalSend(totalSend-error);

        eventMessage.setTotalError(error);
        eventMessage.setStatus(2);
        eventMessage.setUserRecive(name);
        eventMessage.setLastNumber(model.getTo());
        eventMessage.setToken(id);
        eventMessage.setFinalized(finalized);


        publisher.publishCustomWpMessageEvent(eventMessage);

        WpMessageEntity data = new WpMessageEntity();
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "https://graph.facebook.com/v14.0/104478632431473/messages";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("EAAJI2iWWeM8BAKqM02ewnv3nU826ZCAzqzuUj48F4NVnWypxrv0qeL6ls4Hv6udgw2EIZCtwYuFNVP0lZBZACW6WPyh0gzMZAtLqA1YdHAEnBFRPHQoJEZCw2XiMbn6Dskd61aUqbKm7ZC1HYr7mwUWxOvbTWZCah7HHmT9080jPNSbZB69zUa5ZBUi8lhRvaDwHipYfjvZAReF3sGbiivBPxhMUvOkgAKtQl8ZD");
        Map<String, Object> params = new HashMap<String, Object>();


        params.put("messaging_product", "whatsapp");
        params.put("to", "5511947071537");
        params.put("type", "template");
        params.put("template", model.getTemplate());

        String to = model.getTo();
        data.setMessageToNumber(to);
        data.setMessageTemplate(model.getTemplate().getName());
        data.setUserSend("Emison");
        try {
            HttpEntity<?> request = new HttpEntity<>(params, headers);

            ResponseEntity<WpSendMessageResponseModel> result = restTemplate.postForEntity(baseUrl, request, WpSendMessageResponseModel.class);
            Map<String, String> message = (Map<String, String>) result.getBody().messages.get(0);

            String messageId = message.get("id");
            data.setMessageId(messageId.substring(6));
            data.setMessageStatus("1");
            data.setEventId(id);
            data.setFileName(fileName);
            service.saveResponse(data);

            eventMessage.setTotalMessage(totalMessage);
            eventMessage.setUserSend("Emison");
            eventMessage.setTotalSend(totalSend);
            eventMessage.setTotalSend(totalSend-error);
            eventMessage.setTotalError(error);
            eventMessage.setStatus(1);
            eventMessage.setUserRecive(name);
            eventMessage.setLastNumber(model.getTo());
            eventMessage.setToken(id);
            eventMessage.setFinalized(finalized);


            publisher.publishCustomWpMessageEvent(eventMessage);

            return ResponseEntity.ok(result.getBody());

        } catch (Exception e) {
            var errormessage = e.getMessage();
            String generatedString = RandomStringUtils.random(10, true, true);
            data.setMessageId("Error " + generatedString);
            data.setMessageStatus("3");
            data.setEventId(id);
            data.setFileName(fileName);
            service.saveResponse(data);



            eventMessage.setTotalMessage(totalMessage);
            eventMessage.setUserSend("Emison");
            eventMessage.setTotalSend(totalSend-error);
            eventMessage.setTotalError(error);
            eventMessage.setStatus(Integer.valueOf(data.getMessageStatus()));
            eventMessage.setUserRecive(name);
            eventMessage.setLastNumber(model.getTo());
            eventMessage.setToken(id);
            eventMessage.setFinalized(finalized);


            publisher.publishCustomWpMessageEvent(eventMessage);

            return ResponseEntity.badRequest().body(MessageFormat.format(
                    "Não foi Possivel enviar a mensssagem para {0} \n" +
                            "Falha: {1} \n" +
                            "Erro Message Id:{2} "
                    ,
                    to, errormessage, generatedString));


        }


    }
}
