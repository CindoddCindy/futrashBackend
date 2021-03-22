package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.model.ConfirmOrder;
import futrashapi.futrashapiproject.flow_handle.repository.ConfirmOrderRepository;
import futrashapi.futrashapiproject.flow_handle.services.ConfirmOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ConfirmOrderController {

    private ConfirmOrderService confirmOrderService;
    private ConfirmOrderRepository confirmOrderRepository;

    public ConfirmOrderController(ConfirmOrderService confirmOrderService, ConfirmOrderRepository confirmOrderRepository) {
        this.confirmOrderService = confirmOrderService;
        this.confirmOrderRepository = confirmOrderRepository;
    }

    @PostMapping("/confirm/create")
    public ResponseEntity<Object> createConfirm(@RequestBody ConfirmOrder confirmOrder) {
        return  confirmOrderService.addConfirm(confirmOrder);
    }
    @DeleteMapping("/confirm/delete/{id}")
    public ResponseEntity<Object> deleteConfirm(@PathVariable Long id) {
        return confirmOrderService.deleteConfirmOrder(id);
    }
    @GetMapping("/confirm/details/{id}")
    public ConfirmOrder getConfirmOrder(@PathVariable Long id) {
        if(confirmOrderRepository.findById(id).isPresent())
            return confirmOrderRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/confirm/all")
    public List<ConfirmOrder> getConfirm() {
        return confirmOrderRepository.findAll();
    }
}
