package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.Word;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word , Long> {

    @Query("select t from Word t")
    List<Word> findAllWords(Pageable pageable);

    @Query("select t from Word t where t.name in :names ")
    List<Word> findWordsByNames(@Param("names") List<String> name);

}
