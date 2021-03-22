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
        newConfirmOrder.setTerima_tolak(confirmOrder.getTerima_tolak());
        confirmOrder.setCatatan_alasan(confirmOrder.getCatatan_alasan());

        newConfirmOrder.setOrderList(confirmOrder.getOrderList());
        ConfirmOrder savedConfirmOrder = confirmOrderRepository.save(confirmOrder);
        if (confirmOrderRepository.findById(savedConfirmOrder.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");
    }

    /**
     * Delete a specified role given the id
     */
    public ResponseEntity<Object> deleteConfirmOrder(Long id) {
        if (confirmOrderRepository.findById(id).isPresent()) {
            confirmOrderRepository.deleteById(id);
            if (confirmOrderRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
}
