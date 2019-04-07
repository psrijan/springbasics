package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.response.WordSetDetail;
import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.entity.WordGroup;
import com.srijan.springfundamentals.repository.WordRepository;
import com.srijan.springfundamentals.repository.WordSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WordSetService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordSetRepository wordSetRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<WordDetail> wordsInSet(Long wordGroupId) {
        Optional<WordGroup> optionalWordGroup = wordSetRepository.findById(wordGroupId);
        WordGroup wordGroup =optionalWordGroup.orElseThrow(() -> new EntityNotFoundException("Unable to find any Word Group"));
        List<Word> words =wordGroup.getWordList();
        return words.stream()
                .map(word -> modelMapper.map(word, WordDetail.class))
                .collect(Collectors.toList());
    }

    public List<WordSetDetail> wordSetDetailsOnSetType(String setType) {
        Optional<List<WordGroup>> optionalWordGroup = wordSetRepository.findAllByType(setType);
        List<WordGroup> wordGroup =optionalWordGroup.orElseThrow(() -> new EntityNotFoundException("Unable to find Word Group"));
        return wordGroup.stream().map(wordGroup1 -> modelMapper.map(wordGroup, WordSetDetail.class)).collect(Collectors.toList());
    }

}
