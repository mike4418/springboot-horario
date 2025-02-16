package com.mike.curso.springboot.calendar.interceptor.springboot_horario.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AppController {


    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {


        Map<String, Object> data = new HashMap<>();
    
        data.put("tittle", "Welcome to the customer service system");
        data.put("time", new Date());
        data.put("Message", request.getAttribute("message"));
        return ResponseEntity.ok(data);
    }

}
