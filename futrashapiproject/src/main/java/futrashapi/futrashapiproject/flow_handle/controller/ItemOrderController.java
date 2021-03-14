package futrashapi.futrashapiproject.flow_handle.controller;


import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
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
@RequestMapping("/api/v1/orders")
public class ItemOrderController {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ItemOrderController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }


    @PostMapping
    public ResponseEntity<Item> create(@RequestBody @Valid Item item) {
        Optional<Order> optionalLibrary = orderRepository.findById(item.getOrder().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        item.setOrder(optionalLibrary.get());

        Item savedItem = itemRepository.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody @Valid Item item, @PathVariable String id) {
        Optional<Order> optionalLibrary = orderRepository.findById(item.getOrder().getId());
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        item.setOrder(optionalLibrary.get());
        item.setId(optionalItem.get().getId());
        itemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable String id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        itemRepository.delete(optionalItem.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Item>> getAll(Pageable pageable) {
        return ResponseEntity.ok(itemRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable String id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalItem.get());
    }

}
