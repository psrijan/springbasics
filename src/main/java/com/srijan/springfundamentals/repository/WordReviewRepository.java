package com.srijan.springfundamentals.repository;

import com.srijan.springfundamentals.entity.UserWordReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordReviewRepository extends JpaRepository<UserWordReview , Long> {

    @Query("select t from UserWordReview t where t.applicationUser.id = :userId and t.word.id = :wordId")
    Optional<UserWordReview> findUserReview(@Param("wordId") Long wordId, @Param("userId") Long userId);
}
