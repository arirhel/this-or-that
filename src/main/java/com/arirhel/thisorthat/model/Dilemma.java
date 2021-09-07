package com.arirhel.thisorthat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dilemma {

    @Id
    private String id;

    private String question;

    private List<Candidate> candidates;

    // Consider adding a DilemmaDto and updating the DilemmaController when adding new fields

}
