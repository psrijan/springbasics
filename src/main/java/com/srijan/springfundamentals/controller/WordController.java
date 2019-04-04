package com.srijan.springfundamentals.controller;

import com.srijan.springfundamentals.dto.request.CreateWordRequest;
import com.srijan.springfundamentals.dto.request.UpdateWordRequest;
import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.service.impl.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("word")
@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse addWord(CreateWordRequest createWordRequest) {
        return wordService.addNewWord(createWordRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordDetail> listAllWords(Pageable pageable) {
        return wordService.fetchAllWords(pageable);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse updateWord(UpdateWordRequest updateWordRequest) {
        return wordService.updateWord(updateWordRequest);
    }

    @PostMapping(path = "/list" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public List listSpecificWords(List<String> words) {
        return wordService.fetchSpecificWords(words);
    }

}
