package com.stonemason.cqrseventsourcing.command.api.controller;

import com.stonemason.cqrseventsourcing.command.api.commands.CreateProductCommand;
import com.stonemason.cqrseventsourcing.command.api.commands.UpdateProductCommand;
import com.stonemason.cqrseventsourcing.core.model.ProductRestModel;
import com.stonemason.cqrseventsourcing.core.model.ProductUpdateModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel request) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity()).build();

        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }

    @PutMapping
    public String updateProduct(@RequestBody ProductUpdateModel request) {
        UpdateProductCommand updateProductCommand = UpdateProductCommand.builder()
                .productId(request.getProductId())
                .name(request.getName())
                .price(request.getPrice()).build();

        String result = commandGateway.sendAndWait(updateProductCommand);
        return result;
    }

}
