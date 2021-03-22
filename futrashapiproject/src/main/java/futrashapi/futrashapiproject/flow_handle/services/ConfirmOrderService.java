package futrashapi.futrashapiproject.flow_handle.services;

import futrashapi.futrashapiproject.flow_handle.model.ConfirmOrder;
import futrashapi.futrashapiproject.flow_handle.repository.ConfirmOrderRepository;
import futrashapi.futrashapiproject.flow_handle.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfirmOrderService {

    private ConfirmOrderRepository confirmOrderRepository;

    private OrderRepository orderRepository;

    public ConfirmOrderService(ConfirmOrderRepository confirmOrderRepository, OrderRepository orderRepository) {
        this.confirmOrderRepository = confirmOrderRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Create a new role along with users
     */

    @Transactional
    public ResponseEntity<Object> addConfirm(ConfirmOrder confirmOrder) {

        ConfirmOrder newConfirmOrder = new ConfirmOrder();
        newOrderReview.setMitra_name(orderReview.getMitra_name());
        newOrderReview.setCustomer_name(orderReview.getCustomer_name());
        newOrderReview.setFood_name(orderReview.getFood_name());
        newOrderReview.setFood_location(orderReview.getFood_location());
        newOrderReview.setReview_customer(orderReview.getReview_customer());

        newOrderReview.setOrderList(orderReview.getOrderList());
        OrderReview savedOrderReview = orderReviewRepository.save(newOrderReview);
        if (orderReviewRepository.findById(savedOrderReview.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");
    }

    /**
     * Delete a specified role given the id
     */
    public ResponseEntity<Object> deleteOrderReview(Long id) {
        if (orderReviewRepository.findById(id).isPresent()) {
            orderReviewRepository.deleteById(id);
            if (orderReviewRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
}
