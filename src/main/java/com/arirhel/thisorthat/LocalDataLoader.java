package com.arirhel.thisorthat;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.service.DilemmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile("local")
@Component
public class LocalDataLoader implements ApplicationRunner {

    private final DilemmaService dilemmaService;

    @Autowired
    public LocalDataLoader(DilemmaService dilemmaService) {
        this.dilemmaService = dilemmaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Dilemma dilemma = new Dilemma();
        dilemma.setId("6123d06dc0a7df67166b137f");
        dilemma.setQuestion("What should I do for work?");
        Candidate pa = new Candidate();
        pa.setValue("Physician Assistant");
        Candidate sd = new Candidate();
        sd.setValue("Software Developer");
        Candidate bc = new Candidate();
        bc.setValue("Bike Courier");
        Candidate bp = new Candidate();
        bp.setValue("Body Painter");
        Candidate v = new Candidate();
        v.setValue("Veterinarian");
        dilemma.setCandidates(Arrays.asList(pa, sd, bc, bp, v));
        System.out.printf("Saving %s", dilemma);
        dilemmaService.save(dilemma);
    }

}
