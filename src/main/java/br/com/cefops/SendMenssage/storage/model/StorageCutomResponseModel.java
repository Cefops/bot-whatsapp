package br.com.cefops.SendMenssage.storage.model;

import lombok.Data;

@Data
public class StorageCutomResponseModel {
        private String customUrl;
        private String token;
        private String fileName;
        private int totalOfMessages;
        private String userSend;
}
