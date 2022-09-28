package com.stonemason.cqrseventsourcing.command.api.events;

import com.stonemason.cqrseventsourcing.core.data.Product;
import com.stonemason.cqrseventsourcing.core.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(event, product);
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductUpdatedEvent event) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(event.getProductId());
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(event.getName());
            product.setPrice(event.getPrice());
            productRepository.save(product);
        }
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

}
