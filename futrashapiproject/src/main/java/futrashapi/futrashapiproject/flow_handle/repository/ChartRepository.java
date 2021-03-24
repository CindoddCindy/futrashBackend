package futrashapi.futrashapiproject.flow_handle.repository;

import futrashapi.futrashapiproject.flow_handle.model.Chart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {

    Page<Chart> findByItemId(Long itemId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Chart c WHERE c.item.id = ?1")
    void deleteByItemId(Long itemId);
}
