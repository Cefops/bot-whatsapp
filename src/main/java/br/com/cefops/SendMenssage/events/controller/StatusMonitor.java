package br.com.cefops.SendMenssage.events.controller;

import br.com.cefops.SendMenssage.events.service.CustomWpMessageEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status-follow")
public class StatusMonitor {
    @Autowired
    private CustomWpMessageEventService service;
    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable String id ){
var data=service.getById(id);
        return ResponseEntity.ok(data);

    }
}
