package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.Sentence;
import com.srijan.springfundamentals.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence ,Long> {

    Optional<List<Sentence>> findSentenceByWord(Word word);
}
