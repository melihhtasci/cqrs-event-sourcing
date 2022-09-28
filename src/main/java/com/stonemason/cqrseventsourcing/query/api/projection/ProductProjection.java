package com.stonemason.cqrseventsourcing.query.api.projection;

import com.stonemason.cqrseventsourcing.core.data.Product;
import com.stonemason.cqrseventsourcing.core.data.ProductRepository;
import com.stonemason.cqrseventsourcing.core.model.ProductRestModel;
import com.stonemason.cqrseventsourcing.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery query) {
        List<Product> products = productRepository.findAll();

        List<ProductRestModel> productRestModels = products.stream()
                        .map(product -> ProductRestModel.builder()
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build()).collect(Collectors.toList());

        return productRestModels;
    }
}
