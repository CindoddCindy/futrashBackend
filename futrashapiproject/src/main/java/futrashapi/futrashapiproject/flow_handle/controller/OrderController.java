package futrashapi.futrashapiproject.flow_handle.controller;


import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.*;
import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.repository.ChartRepository;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

public class OrderController {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid Order order) {
        Optional<Item> optionalItem = itemRepository.findById(order.getItem().getId());
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        order.setItem(optionalItem.get());

        Order savedOrder = orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(location).body(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@RequestBody @Valid Order order, @PathVariable Long id) {
        Optional<Item> optionalItem = itemRepository.findById(order.getItem().getId());
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        order.setItem(optionalItem.get());
        order.setId(optionalOrder.get().getId());
        orderRepository.save(order);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderRepository.delete(optionalOrder.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Order>> getAll(Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Optional<Order> optionalOrder =orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<Order>> getByItemId(@PathVariable String itemId, Pageable pageable) {
        return ResponseEntity.ok(orderRepository.findByItemId(itemId, pageable));
    }



}
