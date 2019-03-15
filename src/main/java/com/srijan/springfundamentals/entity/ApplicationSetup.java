package com.srijan.springfundamentals.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "APPLICATION_SETUP")
public class ApplicationSetup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "APPLICATION_KEY")
    private String applicationKey;
    @Column(name = "APPLICATION_VALUE")
    private String applicationValue;
    @Column(name = "APPLICATION_LABEL")
    private String applicationLabel;
    @Column(name = "ALLOWED_VALUE")
    private String allowedValue;
    @Column(name = "SETUP_INPUT_TYPE", length = 20)
    private String setupInputType;
    @Column(name = "IS_CONFIGURABLE", length = 2, nullable = false)
    private String isConfigurable;

    public ApplicationSetup() {
    }

}
