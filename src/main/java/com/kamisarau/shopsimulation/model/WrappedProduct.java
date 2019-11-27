package com.kamisarau.shopsimulation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "WRAPPED_PRODUCT")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString
public class WrappedProduct extends AbstractRectangularItem implements Rotatable, Serializable {
    @Id
    @SequenceGenerator(name = "pk_wrapped_product_sequence", sequenceName = "entity_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_wrapped_product_sequence")
    @Column(name = "WRAPPED_PRODUCT_ID")
    private long id;
    @Audited
    @Column(name = "WRAPPED_PRODUCT_NAME")
    private String name;
    @Audited
    @Column(name = "WRAPPED_PRODUCT_PRICE")
    private double price;
    @Column(name = "WRAPPERD_PRODUCT_WIDTH")
    @JsonIgnore
    private int width;
    @Column(name = "WRAPPED_PRODUCT_HEIGHT")
    @JsonIgnore
    private int height;
    @Column(name = "WRAPPERD_PRODUCT_CATEGORY")
    @Enumerated(value = EnumType.STRING)
    @JsonIgnore
    private Category category;
}

