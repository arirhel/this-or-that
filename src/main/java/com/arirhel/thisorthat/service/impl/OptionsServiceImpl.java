package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.dto.OptionsDto;
import com.arirhel.thisorthat.service.OptionsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OptionsServiceImpl implements OptionsService {

    @Override
    public OptionsDto getOptionsById(long id) {
        // In the future this will retrieve options via repository
        if (id == 1) {
            List<String> options = new ArrayList<>();
            options.add("programmer");
            options.add("doctor");
            options.add("lawyer");
            options.add("advocate");
            options.add("comedian");
            return new OptionsDto(1, "What do I want to do for work?", options);
        } else {
            throw new IllegalArgumentException(String.format("No options with id %d", id));
        }
    }

}
