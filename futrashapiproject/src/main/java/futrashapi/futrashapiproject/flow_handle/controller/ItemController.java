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
import java.util.List;

@RestController
@RequestMapping("/api/foodTrash/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/AllItems")
    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }


    @GetMapping("/showItems")
    public List<Item> showAllItems() {
        return itemRepository.findAll();

    }

    @GetMapping("/users/{userId}/items")
    public Page<Item> getAllItemByUserId(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                             Pageable pageable) {
        return itemRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/items")
    public Item createItem(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Item item) {
        return userRepository.findById(userId).map(user -> {
            item.setUser(user);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/items/{itemId}")
    public Item updateItem(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody Item itemRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return itemRepository.findById(itemId).map(item -> {
            item.setJenis_makanan(itemRequest.getJenis_makanan());
            item.setTidak_dikonsumsi_sejak(itemRequest.getTidak_dikonsumsi_sejak());
            item.setDijual_karena(itemRequest.getDijual_karena());
            item.setBerat_makanan(itemRequest.getBerat_makanan());
            item.setNama_toko(itemRequest.getNama_toko());
            item.setNama_penjual(itemRequest.getNama_penjual());
            item.setLokasi_makanan(itemRequest.getLokasi_makanan());
            item.setHarga_makanan(itemRequest.getHarga_makanan());
            item.setSaran_penggunaan(itemRequest.getSaran_penggunaan());
            item.setKandungan_kimia(itemRequest.getKandungan_kimia());

            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + "not found"));
    }

    @DeleteMapping("/users/{userId}/items/{itemId}")
    public ResponseEntity<?> deleteItem(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "itemId") Long itemId) {
        return itemRepository.findByIdAndUserId(itemId, userId).map(item -> {
            itemRepository.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + itemId + " and userId " + userId));
    }


}
