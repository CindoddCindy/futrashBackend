package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemControllerDua {

    private ItemRepository itemRepository;

    public ItemControllerDua(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items/details/{id}")
    public Item getItem(@PathVariable String id) {
        if (itemRepository.findById(id).isPresent())
            return itemRepository.findById(id).get();
        else return null;
    }

    @GetMapping("/items/all")
    public List<Item> getItem() {
        return itemRepository.findAll();
    }
}
