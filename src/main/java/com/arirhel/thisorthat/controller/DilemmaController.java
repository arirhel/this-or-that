package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.service.DilemmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/dilemma")
public class DilemmaController {

    private final DilemmaService dilemmaService;

    @Autowired
    public DilemmaController(DilemmaService dilemmaService) {
        this.dilemmaService = dilemmaService;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Dilemma form) {
        System.out.println(form.toString()); // todo save
        return "redirect:/dilemma/detail?id=" + form.getId();
    }

    @GetMapping("/list")
    public ModelAndView list(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {
        final ModelAndView modelAndView = new ModelAndView("dilemma/list");
        final Page<Dilemma> dilemmaPage = dilemmaService.findAll(page, size);
        modelAndView.addObject("dilemmas", dilemmaPage.getContent());
        modelAndView.addObject("currentPage", dilemmaPage.getPageable().getPageNumber());
        modelAndView.addObject("size", dilemmaPage.getSize());
        modelAndView.addObject("ofPages", dilemmaPage.getTotalPages() - 1); // Offset for zero page
        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(name = "id") String id) {
        final ModelAndView modelAndView = new ModelAndView();
        final Optional<Dilemma> optionalDilemma = dilemmaService.findById(id);
        if (optionalDilemma.isPresent()) {
            modelAndView.setViewName("dilemma/detail");
            modelAndView.addObject("form", optionalDilemma.get());
            modelAndView.addObject("id", optionalDilemma.get().getId());
            modelAndView.addObject("question", optionalDilemma.get().getQuestion());
            modelAndView.addObject("candidates", optionalDilemma.get().getCandidates());
        } else {
            modelAndView.setViewName("dilemma/notfound");
            modelAndView.addObject("id", id);
        }
        return modelAndView;
    }

    @GetMapping("/decide")
    public ModelAndView decide(@RequestParam(name = "id") String id) {
        final ModelAndView modelAndView = new ModelAndView();
        final Optional<Dilemma> optionalDilemma = dilemmaService.findById(id);
        if (optionalDilemma.isPresent()) {
            modelAndView.setViewName("dilemma/decide");
            modelAndView.addObject("question", optionalDilemma.get().getQuestion());
            modelAndView.addObject("candidates", optionalDilemma.get().getCandidates());
        } else {
            modelAndView.setViewName("dilemma/notfound");
            modelAndView.addObject("id", id);
        }
        return modelAndView;
    }

}
