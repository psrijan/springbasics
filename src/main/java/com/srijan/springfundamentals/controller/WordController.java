package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.CreateWordRequest;
import com.srijan.springfundamentals.dto.request.UpdateWordRequest;
import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.service.impl.WordService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("word")
@RestController
public class WordController {

    @Autowired
    private WordService wordService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addWord(@RequestBody CreateWordRequest createWordRequest) {
        log.info("Inside Add Word...");
        return wordService.addNewWord(createWordRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordDetail> listAllWords(Pageable pageable) {
        log.info("Inside List Word");
        return wordService.fetchAllWords(pageable);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateWord(@RequestBody UpdateWordRequest updateWordRequest) {
        log.info("Inside Update Word...");
        return wordService.updateWord(updateWordRequest);
    }

    @PostMapping(path = "/list" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<WordDetail> listSpecificWords(@RequestBody List<String> words) {
        log.info("Inside Specific Word...");
        return wordService.fetchSpecificWords(words);
    }

}
