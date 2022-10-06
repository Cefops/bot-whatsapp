package br.com.cefops.SendMenssage.meta.whatsapp.services;

import br.com.cefops.SendMenssage.meta.whatsapp.data.WpMessageEntity;
import br.com.cefops.SendMenssage.meta.whatsapp.repository.WpMessageEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveResponseService {
    @Autowired
    private WpMessageEntityRepository repository;
    public SaveResponseService(WpMessageEntityRepository repository) {
        this.repository = repository;
    }

    public  void  saveResponse(WpMessageEntity data){
            try {
                repository.save(data);
            }catch (Exception e
            ){
            }

    }

}
