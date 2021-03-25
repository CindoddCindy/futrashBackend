package futrashapi.futrashapiproject.flow_handle.repository;

import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.ItemImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ItemImageRepository extends JpaRepository<ItemImage, String> {


}
