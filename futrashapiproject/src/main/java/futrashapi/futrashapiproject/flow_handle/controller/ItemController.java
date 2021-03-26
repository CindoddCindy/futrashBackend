package futrashapi.futrashapiproject.flow_handle.controller;



import futrashapi.futrashapiproject.auth.repository.UserRepository;
import futrashapi.futrashapiproject.flow_handle.exception.ResourceNotFoundException;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/futrash/")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/items")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemRepository.save(item);
    }
/*
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/items")
    public Page<Item> getAllItemByUserId(@PathVariable (value = "userId") Long userId,
                                             Pageable pageable) {
        return itemRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/items")
    public Item createItem(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Item item) {
        return userRepository.findById(userId).map(user -> {
            item.setUser(user);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

 */



}
