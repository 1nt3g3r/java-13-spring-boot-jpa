package com.goit.java13.mvc.feature.chocholate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Chocolate {
    @Id
    @Column(name = "chocolate_type")
    private String type;

    private String description;
}
