package com.ek.extrakeyboards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class TestLoginController {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ResponseBody
    @RequestMapping("/logintest")
    public String logintest() throws IOException {
        return "You are login";
    }

}
