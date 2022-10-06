package br.com.cefops.SendMenssage.storage.services;

import br.com.cefops.SendMenssage.storage.model.StorageCutomResponseModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageCustomResponse {

public StorageCutomResponseModel sendResponseBody(Map<String,Object> data){
    String token = data.get("token").toString();
    String baseURl=data.get("baseUrl").toString()+"/status-follow";

    StorageCutomResponseModel model=new StorageCutomResponseModel();
    model.setCustomUrl(baseURl+"/"+token);
    model.setToken(token);
    model.setFileName(data.get("fileName").toString());
    model.setTotalOfMessages( Integer.valueOf(data.get("totalOfMessages").toString()));
    model.setUserSend("Emison");

    return model;
}

}
