package com.stonemason.cqrseventsourcing.command.api.aggregate;

import com.stonemason.cqrseventsourcing.command.api.commands.CreateProductCommand;
import com.stonemason.cqrseventsourcing.command.api.commands.UpdateProductCommand;
import com.stonemason.cqrseventsourcing.command.api.events.ProductCreatedEvent;
import com.stonemason.cqrseventsourcing.command.api.events.ProductUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    @CommandHandler
    public void ProductAggregateUpdate(UpdateProductCommand command) {
        ProductUpdatedEvent productUpdatedEvent = new ProductUpdatedEvent();
        BeanUtils.copyProperties(command, productUpdatedEvent);
        AggregateLifecycle.apply(productUpdatedEvent);
        // or
        // AggregateLifecycle.apply(new ProductUpdatedEvent(command.getProductId(),
        // command.getName(), command.getPrice()));
    }


    @EventSourcingHandler
    public void onAdd(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
    }

    @EventSourcingHandler
    public void onUpdate(ProductUpdatedEvent event) {
        this.name = event.getName();
        this.price = event.getPrice();
    }
}
