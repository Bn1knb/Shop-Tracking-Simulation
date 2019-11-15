package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "SHELF")
@Data
@Accessors(chain = true)
@PropertySource(value = "classpath:shelf.properties")
@NoArgsConstructor
public class Shelf implements Storable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
