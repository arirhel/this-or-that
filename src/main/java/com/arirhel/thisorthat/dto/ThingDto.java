package com.arirhel.thisorthat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ThingDto {
    private final long id;
    private final String name;
    private final String details;
}
