package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//@todo change to vocabulary_sets
@Getter
@Setter
@Entity
@Table(name = "Word_Group")
public class WordGroup {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String type;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "word_word_group", joinColumns = {@JoinColumn(name = "WORD_GROUP_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "WORD_ID", referencedColumnName = "ID")})
    private List<Word> wordList;

}
