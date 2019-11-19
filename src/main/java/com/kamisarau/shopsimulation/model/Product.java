package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "PRODUCT")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Product implements Serializable {
    @Id
    @Column(name = "PRODUCT_NAME", unique = true)
    private String name;
    @Column(name = "PRODUCT_WIDTH")
    private int width;
    @Column(name = "PRODUCT_HEIGHT")
    private int height;
}

