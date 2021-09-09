package com.arirhel.thisorthat.controller;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.service.DilemmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/dilemma")
@RequiredArgsConstructor
public class DilemmaController {

    /**
     * Thymeleaf model key for a list of {@link Dilemma}s
     */
    private static final String DILEMMAS = "dilemmas";
    /**
     * Thymeleaf model key for an id
     */
    private static final String ID = "id";
    /**
     * Thymeleaf model key for {@link Dilemma#getQuestion()}
     */
    private static final String QUESTION = "question";
    /**
     * Thymeleaf model key for a list of {@link Candidate}s
     */
    private static final String CANDIDATES = "candidates";
    /**
     * Thymeleaf model key for the DTO used in {@link DilemmaController#save(Dilemma)}
     */
    private static final String FORM = "form";

    private final DilemmaService dilemmaService;

    // todo consider tradeoffs https://www.baeldung.com/spring-redirect-and-forward
    // todo validations (>1 candidate, etc...)
    @PostMapping("/save")
    public String save(@ModelAttribute Dilemma dilemma) {
        final Dilemma dilemma1 = dilemmaService.save(dilemma);
        return "redirect:/dilemma/decide?id=" + dilemma1.getId();
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "10") int size) {
        final ModelAndView modelAndView = new ModelAndView("dilemma/list");
        final Page<Dilemma> dilemmaPage = dilemmaService.findAll(page, size);
        modelAndView.addObject(DILEMMAS, dilemmaPage.getContent());
        // Spring Pageable params
        modelAndView.addObject("currentPage", dilemmaPage.getPageable().getPageNumber());
        modelAndView.addObject("size", dilemmaPage.getSize());
        modelAndView.addObject("ofPages", dilemmaPage.getTotalPages() - 1); // Offset for zero page
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        final ModelAndView modelAndView = new ModelAndView("dilemma/detail");
        modelAndView.addObject(FORM, new Dilemma());
        modelAndView.addObject(CANDIDATES, Collections.singletonList(new Candidate()));
        return modelAndView;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(name = ID) String id) {
        final ModelAndView modelAndView = new ModelAndView();
        final Optional<Dilemma> optionalDilemma = dilemmaService.findById(id);
        if (optionalDilemma.isPresent()) {
            modelAndView.setViewName("dilemma/detail");
            modelAndView.addObject(FORM, optionalDilemma.get());
            modelAndView.addObject(ID, optionalDilemma.get().getId());
            modelAndView.addObject(QUESTION, optionalDilemma.get().getQuestion());
            modelAndView.addObject(CANDIDATES, optionalDilemma.get().getCandidates());
        } else {
            modelAndView.setViewName("dilemma/notfound");
            modelAndView.addObject(ID, id);
        }
        return modelAndView;
    }

    @GetMapping("/decide")
    public ModelAndView decide(@RequestParam(name = ID) String id) {
        final ModelAndView modelAndView = new ModelAndView();
        final Optional<Dilemma> optionalDilemma = dilemmaService.findById(id);
        if (optionalDilemma.isPresent()) {
            modelAndView.setViewName("dilemma/decide");
            modelAndView.addObject(QUESTION, optionalDilemma.get().getQuestion());
            modelAndView.addObject(CANDIDATES, optionalDilemma.get().getCandidates());
        } else {
            modelAndView.setViewName("dilemma/notfound");
            modelAndView.addObject(ID, id);
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = ID) String id,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "10") int size) {
        dilemmaService.delete(id);
        return String.format("redirect:/dilemma/list?page=%s&size=%s", page, size);
    }

}
