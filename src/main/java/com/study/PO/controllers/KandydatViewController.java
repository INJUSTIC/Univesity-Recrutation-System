package com.study.PO.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KandydatViewController {
    @GetMapping("/kandydat/")
    public String getMainPage() {
        return "kandydat/main";
    }

    @GetMapping("/kandydat/danepersonalne")
    public String getSkladanieWniosku() {
        return "kandydat/dane_personalne";
    }
}
