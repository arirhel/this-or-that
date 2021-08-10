package com.arirhel.thisorthat.service;

import com.arirhel.thisorthat.dto.ThingDto;

import java.util.List;

public interface ChooseService {
    List<ThingDto> getThings(String category);
}
