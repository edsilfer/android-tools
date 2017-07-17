package com.edsilfer.controller;

import com.edsilfer.domain.entity.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ferna on 6/3/2017.
 */
@RestController
@RequestMapping(value = "/api/v1/device")
public class DeviceController {

    private Logger logger = LoggerFactory.getLogger(DeviceController.class.getName());

    @Autowired
    private AdbAPI adbAPI;

    @GetMapping
    public ResponseEntity<RequestResponse> list() {
        return new ResponseEntity<>(adbAPI.listDevices(), HttpStatus.ACCEPTED);
    }

}
