package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SHOP_STORAGE")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Storage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORAGE_ID", unique = true)
    private Long id;
    @OneToOne(targetEntity = Product.class)
    @JoinTable(name = "STORAGE_PRODUCT",
            joinColumns = {
                    @JoinColumn(name = "STORAGE_ID", referencedColumnName = "STORAGE_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_NAME", referencedColumnName = "PRODUCT_NAME")
            }
    )
    private Product product;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "DEFAULT_AMOUNT")
    private int defaultAmount;
}
