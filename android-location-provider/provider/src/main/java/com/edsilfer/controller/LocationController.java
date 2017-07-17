package com.edsilfer.controller;


import com.edsilfer.domain.entity.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/location")
public class LocationController {

    private Logger logger = LoggerFactory.getLogger(LocationController.class.getName());

    @Autowired
    private AdbAPI adbAPI;

    @PostMapping
    public ResponseEntity<RequestResponse> create(@RequestParam String provider, @RequestParam String device, @RequestParam Double latitude, @RequestParam Double longitude) {
        return new ResponseEntity<>(adbAPI.sendDeviceLocation(provider, device, latitude, longitude), HttpStatus.ACCEPTED);
    }
}
