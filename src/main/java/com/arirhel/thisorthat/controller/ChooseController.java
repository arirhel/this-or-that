package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.dto.OptionsDto;
import com.arirhel.thisorthat.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChooseController {

    @Autowired
    OptionsService optionsService;

    @GetMapping("/choose")
    public String choose(@RequestParam(name = "id")
                                 String id, Model model) {
        final OptionsDto optionsDto = optionsService.getOptionsById(1);
        model.addAttribute("optionsDto", optionsDto);
        return "choose";
    }

}
