package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@DiscriminatorValue("product")
@NoArgsConstructor
public class Product extends AbstractRectangularItem implements Serializable {
    private String name;
}

