package com.stonemason.cqrseventsourcing.core.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
