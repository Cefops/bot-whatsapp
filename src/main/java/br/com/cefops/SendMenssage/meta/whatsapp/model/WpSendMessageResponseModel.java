package br.com.cefops.SendMenssage.meta.whatsapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WpSendMessageResponseModel {
    public  String messaging_product;
    public List contacts;
    public List messages;

}
