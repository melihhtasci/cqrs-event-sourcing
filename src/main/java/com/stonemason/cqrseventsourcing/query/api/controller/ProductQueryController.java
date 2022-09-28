package com.stonemason.cqrseventsourcing.query.api.controller;

import com.stonemason.cqrseventsourcing.core.model.ProductRestModel;
import com.stonemason.cqrseventsourcing.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        List<ProductRestModel> restModelList = queryGateway.query(getProductsQuery,
                ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return restModelList;
    }

}
