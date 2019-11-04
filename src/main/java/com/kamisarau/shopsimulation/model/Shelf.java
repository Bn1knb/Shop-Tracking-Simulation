package com.kamisarau.shopsimulation.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@PropertySource(value = "classpath:shelf.properties")
public class Shelf implements Storable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "shelf")
    private List<RectangularShape> products = new ArrayList<>();
    @Value("${shelf.width}")
    private int width;
    @Value("${shelf.height}")
    private int height;

    @Override
    public void store(RectangularShape item) {
        products.add(item);
    }
}
