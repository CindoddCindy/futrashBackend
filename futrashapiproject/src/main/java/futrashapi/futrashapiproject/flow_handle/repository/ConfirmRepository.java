package futrashapi.futrashapiproject.flow_handle.repository;

import futrashapi.futrashapiproject.flow_handle.model.Chart;
import futrashapi.futrashapiproject.flow_handle.model.Confirm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ConfirmRepository extends JpaRepository<Confirm, Long> {
    Page<Confirm> findByOrderId(Long orderId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Confirm c WHERE c.order.id = ?1")
    void deleteByOrderId(Long orderId);
}