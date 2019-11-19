package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "SHELF")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class Shelf implements Storable, Serializable {
    @Id
    @SequenceGenerator(name = "pk_shelf_sequence", sequenceName = "entity_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_shelf_sequence")
    @Column(name = "SHELF_ID")
    private Long id;
    @OneToMany(targetEntity = WrappedProduct.class)
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
