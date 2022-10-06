package br.com.cefops.SendMenssage.controller;

import br.com.cefops.SendMenssage.meta.whatsapp.services.SendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("menssage")
public class MessageController {

    @GetMapping(value = "/new")
    public ResponseEntity<?> newMessage(){
        return ResponseEntity.ok().build();

    }
}
