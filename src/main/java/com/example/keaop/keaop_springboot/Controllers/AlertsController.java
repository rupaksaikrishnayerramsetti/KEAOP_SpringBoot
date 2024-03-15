package com.example.keaop.keaop_springboot.Controllers;

import com.example.keaop.keaop_springboot.Helpers.EmailService;
import com.example.keaop.keaop_springboot.Helpers.EmailTemplate;
import com.example.keaop.keaop_springboot.Helpers.JWTService;
import com.example.keaop.keaop_springboot.Repository.AlertsRepository;

public class AlertsController {
    private final AlertsRepository arepo;
    private final JWTService js;
    private final EmailService es;
    private final EmailTemplate et;

    public AlertsController(AlertsRepository arepo, JWTService js, EmailService es, EmailTemplate et) {
        this.arepo = arepo;
        this.js = js;
        this.es = es;
        this.et = et;
    }
    
}