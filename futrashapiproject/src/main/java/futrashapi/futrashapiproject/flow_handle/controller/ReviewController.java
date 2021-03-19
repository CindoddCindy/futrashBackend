package futrashapi.futrashapiproject.flow_handle.controller;


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

//seharusnya ini gausah krna review ada dri order , tapi di bkin untuk
@RestController
@RequestMapping("/api/futrash/reviews")
public class ReviewController {

    private final OrderReviewRepository orderReviewRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ReviewController(OrderReviewRepository orderReviewRepository, OrderRepository orderRepository) {
        this.orderReviewRepository = orderReviewRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<OrderReview> create(@Valid @RequestBody OrderReview orderReview) {
        OrderReview savedOrderReview = orderReviewRepository.save(orderReview);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedOrderReview.getId()).toUri();

        return ResponseEntity.created(location).body(savedOrderReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderReview> update(@PathVariable Long id, @Valid @RequestBody OrderReview orderReview) {
        Optional<OrderReview> optionalLibrary = orderReviewRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderReview.setId(optionalLibrary.get().getId());
        orderReviewRepository.save(orderReview);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderReview> delete(@PathVariable Long id) {
        Optional<OrderReview> optionalLibrary = orderReviewRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        orderReviewRepository.delete(optionalLibrary.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderReview> getById(@PathVariable Long id) {
        Optional<OrderReview> optionalLibrary = orderReviewRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalLibrary.get());
    }

    @GetMapping
    public ResponseEntity<Page<OrderReview>> getAll(Pageable pageable) {
        return ResponseEntity.ok(orderReviewRepository.findAll(pageable));
    }
}
