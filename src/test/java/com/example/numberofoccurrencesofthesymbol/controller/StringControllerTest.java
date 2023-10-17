package com.example.numberofoccurrencesofthesymbol.controller;

import com.example.numberofoccurrencesofthesymbol.service.StringService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StringController.class)
class StringControllerTest {

    private final String INPUTSTRING = "aaabbccccc";
    private final String OUTPUTSTRING = "\"b\":2,\"a\":3,\"c\":5";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private StringService stringService;

    @Test
    void analysisString_ShouldBeReturnNumberOfOccurrencesOfTheSymbol() throws Exception {
        Mockito.when(stringService.characterOccurrenceAnalysis(INPUTSTRING))
                .thenReturn(OUTPUTSTRING);

        mvc.perform(get("/api/" + INPUTSTRING))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(OUTPUTSTRING));
    }

    @Test
    void analysisString_ShouldBeReturnLineMissing() throws Exception {

        mvc.perform(get("/api/"))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().string("Line missing"));
    }

    @Test
    void analysisString_ShouldBeReturnBlankLineException() throws Exception {

        mvc.perform(get("/api/    "))
                .andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers
                        .content().string("The string must not be empty"));
    }
}