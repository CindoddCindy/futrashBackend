package futrashapi.futrashapiproject.flow_handle.controller;

import futrashapi.futrashapiproject.auth.repository.UserRepository;
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
@RequestMapping("/api/foodTrash/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/orders")
    public Page<Order> getAllOrderByItemId(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                              Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/users/{userId}/orders")
    public Order createOrder(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                 @Valid @RequestBody Order order) {
        return userRepository.findById(userId).map(user -> {
            order.setUser(user);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/users/{userId}/orders/{orderId}")
    public Order updateOrder(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "orderId") Long orderId,
                                 @Valid @RequestBody Order orderRequest) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("userId " + userId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            order.setCustomer_name(orderRequest.getCustomer_name());
            order.setCustomer_location(orderRequest.getCustomer_location());
            order.setCustomer_phone(orderRequest.getCustomer_phone());
            order.setShipping_type(orderRequest.getShipping_type());
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + "not found"));
    }

    @DeleteMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@RequestHeader("Authorization") String token,@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "orderId") Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, userId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId + " and UserId " + userId));
    }



}
