package com.study.PO.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GoscViewController {
    @GetMapping("/gosc/")
    public String getMainPage() {
        return "gosc/main";
    }

    @GetMapping("/gosc/obliczwskaznik")
    public String getObliczanieWskaznika() {
        return "gosc/obliczanie_wskaznika";
    }

}
