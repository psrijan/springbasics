package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.CreateSentenceRequest;
import com.srijan.springfundamentals.dto.request.UpdateSentenceRequest;
import com.srijan.springfundamentals.dto.response.SentenceDetail;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.service.impl.SentenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("sentence")
public class SentenceController {

    @Autowired
    private SentenceService sentenceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse newSentence(@RequestBody CreateSentenceRequest createSentenceRequest) {
        log.debug("New Sentence...");
        return sentenceService.addNewSentence(createSentenceRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateSentence(@RequestBody UpdateSentenceRequest updateSentenceRequest) {
        return sentenceService.updateSentence(updateSentenceRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SentenceDetail> listAllSentences(Pageable pageable) {
        return sentenceService.findAllSentences(pageable);
    }

    @GetMapping(path = "/{wordId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SentenceDetail> listSpecificSentence(@PathVariable("wordId") Long wordId) {
        return sentenceService.findSentencesForWord(wordId);
    }
}
