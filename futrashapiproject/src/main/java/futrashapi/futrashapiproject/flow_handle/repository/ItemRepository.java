package futrashapi.futrashapiproject.flow_handle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
