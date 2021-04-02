package futrashapi.futrashapiproject.api_search.repository;

import futrashapi.futrashapiproject.flow_handle.model.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryItem extends PagingAndSortingRepository<Item, Long>, JpaSpecificationExecutor<Item>{
}
