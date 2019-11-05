package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Accessors(chain = true)
@PropertySource(value = "classpath:shelf.properties")
@NoArgsConstructor
public class Shelf implements Storable, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "shelf", targetEntity = AbstractRectangularItem.class, fetch = FetchType.EAGER)
    private List<AbstractRectangularItem> items;
    @Value("${shelf.width}")
    private int width;
    @Value("${shelf.height}")
    private int height;

    @Override
    public void store(AbstractRectangularItem item) {
        items.add(item);
    }
}
