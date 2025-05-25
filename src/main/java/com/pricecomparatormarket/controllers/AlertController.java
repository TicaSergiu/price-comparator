package com.pricecomparatormarket.controllers;

import com.pricecomparatormarket.models.entities.Alert;
import com.pricecomparatormarket.services.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/alert/create")
    public ResponseEntity<Alert> createAlert(@RequestParam Map<String, String> value) {
        Alert alert = alertService.createAlert(value);
        return ResponseEntity.ok(alert);
    }

    @GetMapping("/alert/check")
    public boolean checkAlert(@RequestParam String id) {
       return alertService.alertUser(id);
    }

}
