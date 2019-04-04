package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.request.CreateWordRequest;
import com.srijan.springfundamentals.dto.request.UpdateWordRequest;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<WordDetail> fetchAllWords(Pageable pageable) {
        List<Word> words = wordRepository.findAllWords(pageable);
        return words.stream().map(word -> modelMapper.map(word, WordDetail.class)).collect(Collectors.toList());
    }

    public List<WordDetail> fetchSpecificWords(List<String> wordList) {
        List<Word> words = wordRepository.findWordsByNames(wordList);
        return words.stream().map(word -> modelMapper.map(word , WordDetail.class)).collect(Collectors.toList());
    }

    public GenericResponse addNewWord(CreateWordRequest createWordRequest){
        Word word = modelMapper.map(createWordRequest, Word.class);
        wordRepository.save(word);
        return GenericResponse.builder().success(true).message("Successfully Created New Word").build();
    }

    public GenericResponse updateWord(UpdateWordRequest updateWordRequest) {
        Word word = modelMapper.map(updateWordRequest , Word.class);
        wordRepository.save(word);
        return GenericResponse.builder().message("Successfully created Word").success(true).build();
    }

}
