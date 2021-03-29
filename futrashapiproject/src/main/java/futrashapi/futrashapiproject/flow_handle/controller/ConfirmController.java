package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.auth.repository.UserRepository;
import futrashapi.futrashapiproject.flow_handle.exception.ResourceNotFoundException;
import futrashapi.futrashapiproject.flow_handle.model.Confirm;
import futrashapi.futrashapiproject.flow_handle.repository.ConfirmRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/foodTrash/confirm")
public class ConfirmController {

    @Autowired
    private ConfirmRepository confirmRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/confirm")
    public Page<Confirm> getAllConfirmByOrderId(@PathVariable (value = "userId") Long userId,
                                                Pageable pageable) {
        return confirmRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/confirm")
    public Confirm createConfirm(@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Confirm confirm) {
        return userRepository.findById(userId).map(user -> {
            confirm.setUser(user);
            return confirmRepository.save(confirm);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/confirm/{confirmId}")
    public Confirm updateConfirm(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "confirmId") Long confirmId,
                                 @Valid @RequestBody Confirm confirmRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return confirmRepository.findById(confirmId).map(confirm -> {
            confirm.setTerima_tolak(confirmRequest.getTerima_tolak());
            confirm.setCatatan_alasan(confirmRequest.getCatatan_alasan());
            return confirmRepository.save(confirm);
        }).orElseThrow(() -> new ResourceNotFoundException("ConfirmId " + confirmId + "not found"));
    }

    @DeleteMapping("/users/{userId}/confirm/{confirmId}")
    public ResponseEntity<?> deleteConfirm(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "confirmId") Long confirmId) {
        return confirmRepository.findByIdAndUserId(confirmId, userId).map(confirm -> {
            confirmRepository.delete(confirm);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Confirm not found with id " + confirmId + " and orderId " + userId));
    }


}
