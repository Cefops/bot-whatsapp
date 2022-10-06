package br.com.cefops.SendMenssage.events.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomWpMessageEventModel  implements Serializable {
    private static final long serialVersionUID = 280642152385360625L;


    private int totalMessage;
    private int totalError;
    private int totalSend;
    private String lastNumber;
    private String token;
    private String userSend;
    private String userRecive;
    private int status;
    private Boolean finalized;



}
