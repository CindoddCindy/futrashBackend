package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.model.Confirm;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.ConfirmRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
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

public class ConfirmController {

    private final ConfirmRepository confirmRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ConfirmController(ConfirmRepository confirmRepository, OrderRepository orderRepository) {
        this.confirmRepository = confirmRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Confirm> create(@RequestBody @Valid Confirm confirm) {
        Optional<Order> optionalOrder = orderRepository.findById(confirm.getOrder().getId());
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        confirm.setOrder(optionalOrder.get());

        Confirm savedConfirm = confirmRepository.save(confirm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedConfirm.getId()).toUri();

        return ResponseEntity.created(location).body(savedConfirm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Confirm> update(@RequestBody @Valid Confirm confirm, @PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(confirm.getOrder().getId());
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Confirm> optionalConfirm = confirmRepository.findById(id);
        if (!optionalConfirm.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        confirm.setOrder(optionalOrder.get());
        confirm.setId(optionalConfirm.get().getId());
        confirmRepository.save(confirm);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Confirm> delete(@PathVariable Long id) {
        Optional<Confirm> optionalConfirm = confirmRepository.findById(id);
        if (!optionalConfirm.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        confirmRepository.delete(optionalConfirm.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Confirm>> getAll(Pageable pageable) {
        return ResponseEntity.ok(confirmRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Confirm> getById(@PathVariable Long id) {
        Optional<Confirm> optionalConfirm = confirmRepository.findById(id);
        if (!optionalConfirm.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalConfirm.get());
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Page<Confirm>> getByOrderId(@PathVariable Long orderId, Pageable pageable) {
        return ResponseEntity.ok(confirmRepository.findByOrderId(orderId, pageable));
    }




}
