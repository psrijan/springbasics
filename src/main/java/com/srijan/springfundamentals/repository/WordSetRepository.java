package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.WordGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordSetRepository extends JpaRepository<WordGroup , Long> {

    Optional<List<WordGroup>>   findAllByType(String type);

}
