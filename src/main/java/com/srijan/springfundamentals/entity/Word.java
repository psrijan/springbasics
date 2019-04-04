package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="WORD")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME" , length = 40, nullable =  false)
    private String name;

    @Column(name="DEFINITION" , length = 500)
    private String definition;

    public Word() {
    }

    public Word(Long id) {
        this.id = id;
    }
}
