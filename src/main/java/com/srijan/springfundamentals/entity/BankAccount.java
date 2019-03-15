package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="BANK_ACCOUNT")
public class BankAccount implements Serializable {

    @Id
    private Long id;

    @NotNull
    @Column(name = "ACCOUNT_NAME" , length = 40, nullable =  false)
    private String accountName;

}
