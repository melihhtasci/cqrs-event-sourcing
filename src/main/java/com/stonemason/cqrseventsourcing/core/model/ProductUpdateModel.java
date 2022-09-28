package com.stonemason.cqrseventsourcing.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductUpdateModel {

    private String productId;
    private String name;
    private BigDecimal price;

}
