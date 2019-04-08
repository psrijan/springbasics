package com.srijan.springfundamentals.service.impl;

import com.srijan.springfundamentals.dto.request.CreateWordRequest;
import com.srijan.springfundamentals.dto.request.UpdateWordRequest;
import com.srijan.springfundamentals.dto.response.WordSetDetail;
import com.srijan.springfundamentals.dto.server.GenericResponse;
import com.srijan.springfundamentals.dto.response.WordDetail;
import com.srijan.springfundamentals.dto.server.WordReviewRequest;
import com.srijan.springfundamentals.entity.UserWordReview;
import com.srijan.springfundamentals.entity.Word;
import com.srijan.springfundamentals.repository.WordRepository;
import com.srijan.springfundamentals.repository.WordReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private WordReviewRepository wordReviewRepository;

    ModelMapper modelMapper = new ModelMapper();

    public List<WordDetail> fetchAllWords(Pageable pageable) {
        log.debug("Fetching Words from Wordservice...");
        List<Word> words = wordRepository.findAllWords(pageable);
        return words.stream().map(word -> modelMapper.map(word, WordDetail.class)).collect(Collectors.toList());
    }

    public List<WordDetail> fetchSpecificWords(List<String> wordList) {
        List<Word> words = wordRepository.findWordsByNames(wordList);
        return words.stream().map(word -> modelMapper.map(word, WordDetail.class)).collect(Collectors.toList());
    }

    public GenericResponse addNewWord(CreateWordRequest createWordRequest) {
        Word word = modelMapper.map(createWordRequest, Word.class);
        wordRepository.save(word);
        return GenericResponse.builder().success(true).message("Successfully Created New Word").build();
    }

    public GenericResponse updateWord(UpdateWordRequest updateWordRequest) {
        Word word = modelMapper.map(updateWordRequest, Word.class);
        wordRepository.save(word);
        return GenericResponse.builder().message("Successfully created Word").success(true).build();
    }

    public GenericResponse updateWordReview(WordReviewRequest wordReviewRequest) {
        log.info("Update Word Review Service...");
        Optional<UserWordReview> optUserWordReview = wordReviewRepository.findUserReview(wordReviewRequest.getWordId(), wordReviewRequest.getUserId());
        UserWordReview userWordReview = null;

        if (optUserWordReview.isPresent()) {
            log.info("Found existing WordReview in db ...");
            userWordReview = optUserWordReview.get();
            userWordReview.setLastReviewedDate(new Date());
            userWordReview.setConsecutiveCorrectCount(wordReviewRequest.getIncorrectCount() == 0 ? userWordReview.getConsecutiveCorrectCount() + wordReviewRequest.getConsecutiveCorrectCount() : wordReviewRequest.getConsecutiveCorrectCount());
            userWordReview.setIncorrectCount(userWordReview.getIncorrectCount() + wordReviewRequest.getIncorrectCount());
            userWordReview.setTotalCorrectCount(userWordReview.getTotalCorrectCount() + wordReviewRequest.getCorrectCount());
        } else {
            log.info("No Word Review in db creating new request...");
            userWordReview = new UserWordReview();
            userWordReview.setApplicationUser(wordReviewRequest.getApplicationUser());
            userWordReview.setTotalCorrectCount(wordReviewRequest.getCorrectCount());
            userWordReview.setIncorrectCount(wordReviewRequest.getIncorrectCount());
            userWordReview.setConsecutiveCorrectCount(wordReviewRequest.getConsecutiveCorrectCount());
            userWordReview.setWord(new Word(wordReviewRequest.getWordId()));
        }
        wordReviewRepository.save(userWordReview);
        return new GenericResponse(true, "Successfully Added/Updated new word request");
    }

}
