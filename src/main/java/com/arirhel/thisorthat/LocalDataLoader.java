package com.arirhel.thisorthat;

import com.arirhel.thisorthat.model.Candidate;
import com.arirhel.thisorthat.model.Dilemma;
import com.arirhel.thisorthat.model.User;
import com.arirhel.thisorthat.model.UserRole;
import com.arirhel.thisorthat.repository.DilemmaRepository;
import com.arirhel.thisorthat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Profile("local")
@Component
public class LocalDataLoader implements ApplicationRunner {

    private final UserService userService;

    private final DilemmaRepository dilemmaRepository; // todo go through service instead

    @Autowired
    public LocalDataLoader(UserService userService, DilemmaRepository dilemmaRepository) {
        this.userService = userService;
        this.dilemmaRepository = dilemmaRepository;
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
        dilemmaRepository.save(dilemma);

        User user = new User();
        user.setId("6125402c73db7865061965fc");
        user.setUsername("user");
        user.setPassword("pass");
        UserRole userRole = new UserRole();
        userRole.setRole("USER");
        user.setUserRoles(List.of(userRole));
        System.out.printf("Saving %s", user);
        userService.save(user);
    }

}
