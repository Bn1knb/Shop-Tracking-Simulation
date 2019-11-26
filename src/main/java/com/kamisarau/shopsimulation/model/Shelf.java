package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "SHELF")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Shelf implements Storable, Serializable {
    @Id
    @SequenceGenerator(name = "pk_shelf_sequence", sequenceName = "entity_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_shelf_sequence")
    @Column(name = "SHELF_ID")
    private Long id;
    @OneToMany(targetEntity = WrappedProduct.class)
    @JoinTable(name = "shelf_products",
            joinColumns = @JoinColumn(
                    name = "SHELF_ID",
                    referencedColumnName = "SHELF_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "PRODUCT_ID",
                    referencedColumnName = "WRAPPED_PRODUCT_ID"
            )
    )
    private List<WrappedProduct> products;
    @Column(name = "WIDTH")
    private int width;
    @Column(name = "HEIGHT")
    private int height;

    @Override
    public void store(WrappedProduct product) {
        products.add(product);
    }
}
