package com.arirhel.thisorthat.dto;

import lombok.Data;

import java.util.List;

@Data
public class OptionsDto {
    private final long id;
    private final String question;
    private final List<String> options;
}
