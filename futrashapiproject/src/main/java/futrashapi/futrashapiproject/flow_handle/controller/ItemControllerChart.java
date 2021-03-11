package futrashapi.futrashapiproject.flow_handle.controller;

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

@RestController
@RequestMapping("/api/v1/libraries")
public class ItemControllerChart {

    private final ItemRepository itemRepository;
    private final ChartRepository chartRepository;

    @Autowired
    public ItemControllerChart(ItemRepository itemRepository, ChartRepository chartRepository) {
        this.itemRepository = itemRepository;
        this.chartRepository = chartRepository;
    }

    @PostMapping
    public ResponseEntity<Item> create(@Valid @RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).body(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable String id, @Valid @RequestBody Item item) {
        Optional<Item> optionalLibrary = itemRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        item.setId(optionalLibrary.get().getId());
        itemRepository.save(item);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> delete(@PathVariable String id) {
        Optional<Item> optionalLibrary = itemRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        itemRepository.delete(optionalLibrary.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable String id) {
        Optional<Item> optionalLibrary = itemRepository.findById(id);
        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalLibrary.get());
    }

    @GetMapping
    public ResponseEntity<Page<Item>> getAll(Pageable pageable) {
        return ResponseEntity.ok(itemRepository.findAll(pageable));
    }
}
