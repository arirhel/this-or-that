package com.arirhel.thisorthat.service.impl;

import com.arirhel.thisorthat.service.ShuffleService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShuffleServiceImpl implements ShuffleService {
    @Override
    public List<String> shuffle(List<String> list) {
        Collections.shuffle(list);
        return list;
    }
}
