package futrashapi.futrashapiproject.flow_handle.controller;


import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import futrashapi.futrashapiproject.flow_handle.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;
    private OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order/create")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        return  orderService.addOrder(order);
    }
    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
    @GetMapping("/order/details/{id}")
    public Order getOrder(@PathVariable Long id) {
        if(orderRepository.findById(id).isPresent())
            return orderRepository.findById(id).get();
        else return null;
    }
    @GetMapping("/order/all")
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }
}
