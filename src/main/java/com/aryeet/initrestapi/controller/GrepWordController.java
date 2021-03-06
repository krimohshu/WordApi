package com.aryeet.initrestapi.controller;

import com.aryeet.initrestapi.response.ResponseDTO;
import com.aryeet.initrestapi.service.GrepWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class GrepWordController {

    @Autowired
    private GrepWordService grepWordService;

    @Autowired
    ResponseDTO responseDTO;

    @GetMapping("/longword")
    @ResponseBody
    public ResponseDTO getLongWordProvidedInUrl(@RequestParam("sentence") String sentence) {
        return responseDTO.getResponseBody(grepWordService.getLongestWord(sentence));
    }

}
