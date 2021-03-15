package futrashapi.futrashapiproject.flow_handle.services;


import futrashapi.futrashapiproject.flow_handle.model.Order;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * Create a new role along with users
     */

    @Transactional
    public ResponseEntity<Object> addOrder(Order order) {

        Order newOrder = new Order();
        newOrder.setImage_url(order.getImage_url());
        newOrder.setFood_name(order.getFood_name());
        newOrder.setMitra_name(order.getMitra_name());
        newOrder.setFood_location(order.getFood_location());
        newOrder.setFood_price(order.getFood_price());
        newOrder.setCustomer_name(order.getCustomer_name());
        newOrder.setCustomer_location(order.getCustomer_location());
        newOrder.setCustomer_phone(order.getCustomer_phone());
        newOrder.setShipping_type(order.getShipping_type());

        newOrder.setItemList(order.getItemList());
        Order savedOrder = orderRepository.save(newOrder);
        if (orderRepository.findById(savedOrder.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");
    }

    /**
     * Delete a specified role given the id
     */
    public ResponseEntity<Object> deleteOrder(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            if (orderRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
}
