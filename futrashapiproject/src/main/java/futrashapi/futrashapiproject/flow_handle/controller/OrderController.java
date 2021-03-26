package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.flow_handle.exception.ResourceNotFoundException;
import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items/{itemId}/orders")
    public Page<Order> getAllOrderByItemId(@PathVariable (value = "itemId") Long itemId,
                                              Pageable pageable) {
        return orderRepository.findByItemId(itemId, pageable);
    }

    @PostMapping("/items/{itemId}/orders")
    public Order createOrder(@PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody Order order) {
        return itemRepository.findById(itemId).map(item -> {
            order.setItem(item);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + " not found"));
    }

    @PutMapping("/items/{itemId}/orders/{orderId}")
    public Order updateOrder(@PathVariable (value = "itemId") Long itemId,
                                 @PathVariable (value = "orderId") Long orderId,
                                 @Valid @RequestBody Order orderRequest) {
        if(!itemRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("ItemId " + itemId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            order.setCustomer_name(orderRequest.getCustomer_name());
            order.setCustomer_location(orderRequest.getCustomer_location());
            order.setCustomer_phone(orderRequest.getCustomer_phone());
            order.setShipping_type(orderRequest.getShipping_type());
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + "not found"));
    }

    @DeleteMapping("/items/{itemId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable (value = "itemId") Long itemId,
                                           @PathVariable (value = "orderId") Long orderId) {
        return orderRepository.findByIdAndItemId(orderId, itemId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId + " and ItemId " + itemId));
    }



}
