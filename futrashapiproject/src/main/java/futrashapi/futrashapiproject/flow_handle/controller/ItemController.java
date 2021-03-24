package futrashapi.futrashapiproject.flow_handle.controller;


import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.auth.repository.UserRepository;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/api/futrash/item")
public class ItemController {


    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestHeader("Authorization") String token, @RequestBody @Valid Item item) {
        Optional<User> optionalUser = userRepository.findById(item.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        item.setUser(optionalUser.get());

        Item savedItem = itemRepository.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@RequestBody @Valid Item item, @PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(item.getUser().getId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        item.setUser(optionalUser.get());
        item.setId(optionalItem.get().getId());
        itemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id) {
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
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (!optionalItem.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalItem.get());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Item>> getByUserId(@PathVariable Long userId, Pageable pageable) {
        return ResponseEntity.ok(itemRepository.findByUserId(userId, pageable));
    }



}
