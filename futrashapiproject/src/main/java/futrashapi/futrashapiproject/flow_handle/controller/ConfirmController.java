package futrashapi.futrashapiproject.flow_handle.controller;

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
public class ConfirmController {

    @Autowired
    private ConfirmRepository confirmRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders/{orderId}/confirm")
    public Page<Confirm> getAllConfirmByOrderId(@PathVariable (value = "orderId") Long orderId,
                                                Pageable pageable) {
        return confirmRepository.findByOrderId(orderId, pageable);
    }

    @PostMapping("/orders/{orderId}/confirm")
    public Confirm createConfirm(@PathVariable (value = "orderId") Long orderId,
                                 @Valid @RequestBody Confirm confirm) {
        return orderRepository.findById(orderId).map(order -> {
            confirm.setOrder(order);
            return confirmRepository.save(confirm);
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));
    }

    @PutMapping("/orders/{orderId}/confirm/{confirmId}")
    public Confirm updateConfirm(@PathVariable (value = "orderId") Long orderId,
                                 @PathVariable (value = "confirmId") Long confirmId,
                                 @Valid @RequestBody Confirm confirmRequest) {
        if(!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("OrderId " + orderId + " not found");
        }

        return confirmRepository.findById(confirmId).map(confirm -> {
            confirm.setTerima_tolak(confirmRequest.getTerima_tolak());
            confirm.setCatatan_alasan(confirmRequest.getCatatan_alasan());
            return confirmRepository.save(confirm);
        }).orElseThrow(() -> new ResourceNotFoundException("ConfirmId " + confirmId + "not found"));
    }

    @DeleteMapping("/orders/{orderId}/confirm/{confirmId}")
    public ResponseEntity<?> deleteConfirm(@PathVariable (value = "orderId") Long orderId,
                                           @PathVariable (value = "confirmId") Long confirmId) {
        return confirmRepository.findByIdAndOrderId(confirmId, orderId).map(confirm -> {
            confirmRepository.delete(confirm);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Confirm not found with id " + confirmId + " and orderId " + orderId));
    }


}
