package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Storage implements Storable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "storage")
    private List<AbstractRectangularItem> items;

    @Override
    public void store(AbstractRectangularItem item) {
        items.add(item);
    }
}
