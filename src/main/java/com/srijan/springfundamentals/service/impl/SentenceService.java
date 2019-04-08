package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.request.CreateSentenceRequest;
import com.srijan.springfundamentals.dto.request.UpdateSentenceRequest;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.dto.response.SentenceDetail;
import com.srijan.springfundamentals.entity.Sentence;
import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.repository.SentenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SentenceService {

    @Autowired
    private SentenceRepository sentenceRepository;

    ModelMapper modelMapper = new ModelMapper();

    public GenericResponse addNewSentence(CreateSentenceRequest createSentenceRequest) {
        log.info("Add new sentence service");
        Sentence sentence = new Sentence();
        sentence.setWord(new Word(createSentenceRequest.getWordId()));
        sentence.setValue(createSentenceRequest.getSentence());
        sentenceRepository.save(sentence);
        return GenericResponse.builder().message("Successfully Created New Sentence").success(true).build();
    }

    public List<SentenceDetail> findSentencesForWord(Long wordId) {
        log.debug("Find sentence for word service");
        Optional<List<Sentence>> optionalSentences = sentenceRepository.findSentenceByWord(new Word(wordId));
        return optionalSentences
                .orElseThrow(() -> new EntityNotFoundException("Unable to find any sentences"))
                .stream().map(sentence -> modelMapper.map(sentence , SentenceDetail.class))
                .collect(Collectors.toList());
    }

    public List<SentenceDetail> findAllSentences(Pageable pageable) {
        log.debug("Find all sentence service");
        List<Sentence> sentences = sentenceRepository.findAll();
        Optional<List<Sentence>> optionalSentences = Optional.ofNullable(sentences);
        return optionalSentences.orElseThrow(() -> new EntityNotFoundException("Unable to find Sentences"))
                .stream().map(sentence -> modelMapper.map(sentence, SentenceDetail.class)).collect(Collectors.toList());

    }

    public GenericResponse updateSentence(UpdateSentenceRequest updateSentenceRequest ) {
        log.debug("update sentene service");
        Sentence sentence=modelMapper.map(updateSentenceRequest , Sentence.class);
        sentenceRepository.save(sentence);
        return GenericResponse.builder().success(true).message("Successfully Updated Sentence").build();
    }

}
