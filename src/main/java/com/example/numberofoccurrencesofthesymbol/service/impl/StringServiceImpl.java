package com.example.numberofoccurrencesofthesymbol.service.impl;

import com.example.numberofoccurrencesofthesymbol.service.StringService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StringServiceImpl implements StringService {

    @Override
    public String characterOccurrenceAnalysis(String str) {
        Map<String, Integer> map = new HashMap<>();

        Arrays.stream(str.split("")).forEach(s -> {
            map.merge(s, 1, Integer::sum);
        });

        List<Map.Entry<String, Integer>> list =
                map.entrySet().stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getValue))
                        .toList();

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : list) {
            sb.append(String.format("\"%s\":%d,", entry.getKey(), entry.getValue()));
        }

        return sb.substring(0, sb.length() - 1);
    }
}
