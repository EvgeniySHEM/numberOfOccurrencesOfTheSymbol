package com.example.numberofoccurrencesofthesymbol.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StringServiceImplTest {

    @InjectMocks
    private StringServiceImpl stringService;

    @Test
    void characterOccurrenceAnalysis_ShouldBeReturnNumberOfOccurrencesOfTheSymbol() {

        String inputString = "aaabbccccc";
        String outputString = "\"b\":2,\"a\":3,\"c\":5";

        assertEquals(outputString, stringService.characterOccurrenceAnalysis(inputString));
    }
}