package com.arirhel.thisorthat.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Dilemma {

    @Id
    private String id;

    private String question;

    private List<Candidate> candidates;

    // Consider adding a DilemmaDto and updating the DilemmaController when adding new fields

}
