package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SHOP_STORAGE")
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Storage implements Serializable {
    @Id
    @SequenceGenerator(name = "pk_storage_sequense", sequenceName = "entity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_storage_sequence")
    @Column(name = "STORAGE_ID", unique = true)
    private Long id;
    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name="PRODUCT_NAME")
    private Product product;
    @Column(name = "AMOUNT")
    private int amount;
    @Column(name = "DEFAULT_AMOUNT")
    private int defaultAmount;
}
