package com.kamisarau.shopsimulation.model;

import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Accessors(chain = true)
public class PricesChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    private Product product;
    @CreatedDate
    private Date modifiedAt;
}
