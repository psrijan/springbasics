package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.advice.qualifier.LogTimeExecution;
import com.srijan.springfundamentals.dto.request.CreateWordRequest;
import com.srijan.springfundamentals.dto.request.UpdateWordRequest;
import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.service.impl.WordService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.Media;
import java.util.Collections;
import java.util.List;


@RequestMapping("word")
@RestController
public class WordController {

    @Autowired
    private WordService wordService;


    @LogTimeExecution
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addWord(@RequestBody CreateWordRequest createWordRequest) {
        return wordService.addNewWord(createWordRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordDetail> listAllWords(Pageable pageable) {
        return wordService.fetchAllWords(pageable);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateWord(@RequestBody UpdateWordRequest updateWordRequest) {
        return wordService.updateWord(updateWordRequest);
    }

    @PostMapping(path = "/list" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public List listSpecificWords(@RequestBody List<String> words) {
        return wordService.fetchSpecificWords(words);
    }

}
