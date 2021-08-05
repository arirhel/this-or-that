package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.dto.ThingDto;
import com.arirhel.thisorthat.service.ChooseService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ChooseServiceImpl implements ChooseService {
    @Override
    public List<ThingDto> getThings(String category) {
        // In future this will retrieve options via repository
        if ("careers".equals(category)) {
            ThingDto programmer = new ThingDto(1, "programmer", null);
            ThingDto doctor = new ThingDto(2, "doctor", null);
            ThingDto lawyer = new ThingDto(3, "lawyer", null);
            ThingDto advocate = new ThingDto(4, "advocate", null);
            ThingDto comedian = new ThingDto(5, "comedian", null);
            return Arrays.asList(programmer, doctor, lawyer, advocate, comedian);
        } else {
            throw new IllegalArgumentException(String.format("No options of category %s", category));
        }
    }
}
