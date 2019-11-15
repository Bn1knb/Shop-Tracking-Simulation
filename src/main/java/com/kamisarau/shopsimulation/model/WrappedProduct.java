package com.kamisarau.shopsimulation.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity(name = "WRAPPED_PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WrappedProduct extends AbstractRectangularItem implements Rotatable {
    @Id
    @Column(name = "WRAPPED_PRODUCT_NAME", unique = true)
    private String name;
    @Audited
    @Column(name = "WRAPPED_PRODUCT_PRICE")
    private double price;
    @Column(name = "WRAPPERD_PRODUCT_WIDTH")
    private int width;
    @Column(name = "WRAPPED_PRODUCT_HEIGHT")
    private int height;
    @Column(name = "WRAPPERD_PRODUCT_CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private Category category;
}

