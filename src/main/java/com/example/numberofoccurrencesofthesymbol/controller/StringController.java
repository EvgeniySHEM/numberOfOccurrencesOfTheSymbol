package com.example.numberofoccurrencesofthesymbol.controller;

import com.example.numberofoccurrencesofthesymbol.exeption_handling.BlankLineException;
import com.example.numberofoccurrencesofthesymbol.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class StringController {

    private final StringService stringService;

    @Autowired
    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @GetMapping(value = {"/{str}", "/"})
    public ResponseEntity<String> analysisString(@PathVariable Optional<String> str) {

        if (str.isPresent()) {
            if (str.get().isBlank()) {
                throw new BlankLineException("The string must not be empty");
            }
            return new ResponseEntity<>(stringService.characterOccurrenceAnalysis(str.get()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Line missing", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(BlankLineException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
