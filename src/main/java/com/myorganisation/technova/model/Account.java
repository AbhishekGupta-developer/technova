package com.myorganisation.technova.model;

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
}
