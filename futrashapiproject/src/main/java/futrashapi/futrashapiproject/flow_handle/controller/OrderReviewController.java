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

@RestController
@RequestMapping("orderReview")
public class OrderReviewController {

    private final OrderReviewRepository orderReviewRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderReviewController(OrderReviewRepository orderReviewRepository, OrderRepository orderRepository) {
        this.orderReviewRepository = orderReviewRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<OrderReview> create(@RequestBody @Valid OrderReview orderReview) {
        Optional<Order> optionalLibrary = orderRepository.findById(orderReview.getOrder().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderReview.setOrder(optionalLibrary.get());

        OrderReview savedOrderReview = orderReviewRepository.save(orderReview);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrderReview.getId()).toUri();

        return ResponseEntity.created(location).body(savedOrderReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderReview> update(@RequestBody @Valid OrderReview orderReview, @PathVariable Long id) {
        Optional<Order> optionalLibrary = orderRepository.findById(orderReview.getOrder().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<OrderReview> optionalOrderReview = orderReviewRepository.findById(id);
        if (!optionalOrderReview.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderReview.setOrder(optionalLibrary.get());
        orderReview.setId(optionalOrderReview.get().getId());
        orderReviewRepository.save(orderReview);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderReview> delete(@PathVariable Long id) {
        Optional<OrderReview> optionalBook = orderReviewRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderReviewRepository.delete(optionalBook.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<OrderReview>> getAll(Pageable pageable) {
        return ResponseEntity.ok(orderReviewRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderReview> getById(@PathVariable Long id) {
        Optional<OrderReview> optionalBook = orderReviewRepository.findById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalBook.get());
    }
}
