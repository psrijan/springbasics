package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USER_WORD_REVIEW")
public class UserWordReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(mappedBy = "id" , fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID" ,nullable = false)
    private ApplicationUser applicationUser;

    @OneToOne(mappedBy = "id" , fetch = FetchType.EAGER)
    @JoinColumn(name="WORD_ID" , nullable=false)
    private Word word;

    @Column(name="CONSECUTIVE_CORRECT_COUNT")
    private Long consecutiveCorrectCount;

    @Column(name="TOTAL_CORRECT_COUNT")
    private Long totalCorrectCount;

    @Column(name="INCORRECT_COUNT")
    private Long incorrectCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_REVIEWED")
    private Date lastReviewedDate;
}
