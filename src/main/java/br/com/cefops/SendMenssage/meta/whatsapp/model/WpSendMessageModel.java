package br.com.cefops.SendMenssage.meta.whatsapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WpSendMessageModel {
    private String messagingProduct;
    private String to;
    private String type;
    private WpTemplateModel template;
}
