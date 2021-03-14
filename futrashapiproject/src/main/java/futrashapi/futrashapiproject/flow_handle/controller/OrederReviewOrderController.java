package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.model.OrderReview;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
//sebenarnya ini gausah krna order harus berdasarkan item, cuma utk cek flow nya
@RestController
@RequestMapping("/api/v1/books")
public class OrederReviewOrderController {

    private final OrderRepository orderRepository;
    private final OrderReviewRepository orderReviewRepository;

    @Autowired
    public OrederReviewOrderController(OrderRepository orderRepository, OrderReviewRepository orderReviewRepository) {
        this.orderRepository = orderRepository;
        this.orderReviewRepository = orderReviewRepository;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid Order order) {
        Optional<OrderReview> optionalLibrary = orderReviewRepository.findById(order.getOrderReview().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        order.setOrderReview(optionalLibrary.get());

        Order savedOrder = orderRepository.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrder.getId()).toUri();

        return ResponseEntity.created(location).body(savedOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@RequestBody @Valid Order order, @PathVariable Long id) {
        Optional<OrderReview> optionalLibrary = orderReviewRepository.findById(order.getOrderReview().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        order.setOrderReview(optionalLibrary.get());
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
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalOrder.get());
    }
}

