package futrashapi.futrashapiproject.flow_handle.repository;

import futrashapi.futrashapiproject.flow_handle.model.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Chart, Long> {
}
