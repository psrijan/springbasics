package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.response.WordSetDetail;
import com.srijan.springfundamentals.service.impl.WordService;
import com.srijan.springfundamentals.service.impl.WordSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sets")
public class WordSetController {

    @Autowired
    public WordService wordService;

    @Autowired
    private WordSetService wordSetService;

    @GetMapping(path = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordSetDetail> getAllSetNames(@PathVariable("type") String type) {
        log.info("Get All Sets ...");
        return wordSetService.wordSetDetailsOnSetType(type);
    }

    @GetMapping(path = "/{setId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordDetail> getAllWords(@PathVariable("setId") Long setId) {
        log.debug("Get All Words for Sets...");
        return wordSetService.wordsInSet(setId);
    }

}
