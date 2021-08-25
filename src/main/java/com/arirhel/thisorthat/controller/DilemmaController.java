package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.service.DilemmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dilemma")
public class DilemmaController {

    private final DilemmaService dilemmaService;

    @Autowired
    public DilemmaController(DilemmaService dilemmaService) {
        this.dilemmaService = dilemmaService;
    }

    @GetMapping("/list")
    public ModelAndView dilemmaList(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {
        ModelAndView modelAndView = new ModelAndView("dilemma/list");
        Page<Dilemma> dilemmaPage = dilemmaService.findAll(page, size);
        modelAndView.addObject("dilemmas", dilemmaPage.getContent());
        modelAndView.addObject("currentPage", dilemmaPage.getPageable().getPageNumber());
        modelAndView.addObject("size", dilemmaPage.getSize());
        modelAndView.addObject("ofPages", dilemmaPage.getTotalPages() - 1); // Offset for zero page
        return modelAndView;
    }

}
