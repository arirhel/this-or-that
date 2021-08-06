package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.dto.ThingDto;
import com.arirhel.thisorthat.service.ChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChooseController {

    @Autowired
    ChooseService chooseService;

    @GetMapping("/choose")
    public String choose(@RequestParam(name = "category", required = false, defaultValue = "careers")
                                 String category, Model model) {
        final List<ThingDto> things = chooseService.getThings(category);
        model.addAttribute("category", category);
        model.addAttribute("things", things);
        return "choose";
    }

}
