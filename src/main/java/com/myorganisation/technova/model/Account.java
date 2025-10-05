package com.myorganisation.technova.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;
    private Double due;

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "student")
    @JsonIgnore
    private Student student;
}
