package futrashapi.futrashapiproject.api_search.service;

import futrashapi.futrashapiproject.api_search.entity.utils.PagingHeaders;
import futrashapi.futrashapiproject.api_search.entity.utils.PagingResponse;
import futrashapi.futrashapiproject.api_search.repository.RepositoryItem;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ItemService extends GenericCsv<Item>{


    private final RepositoryItem repositoryItem;

    @Autowired
    public ItemService(RepositoryItem repositoryItem) {
        super(Item.class);
        this.repositoryItem = repositoryItem;
    }

    /**
     * delete element
     *
     * @param id element ID
     * @throws EntityNotFoundException Exception when retrieve entity
     */
    public void delete(Long id) {
        Item entity = repositoryItem.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity item (%s = %s).", "id", id.toString())));
        repositoryItem.delete(entity);
    }

    /**
     * @param id element ID
     * @return element
     * @throws EntityNotFoundException Exception when retrieve element
     */
    public Item get(Long id) {
        return repositoryItem.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can not find the entity item (%s = %s).", "id", id.toString())));
    }

    /**
     * get element using Criteria.
     *
     * @param spec    *
     * @param headers pagination data
     * @param sort    sort criteria
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Item> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            List<Item> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

    /**
     * get elements using Criteria.
     *
     * @param spec     *
     * @param pageable pagination data
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Item> spec, Pageable pageable) {
        Page<Item> page = repositoryItem.findAll(spec, pageable);
        List<Item> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    /**
     * get elements using Criteria.
     *
     * @param spec *
     * @return elements
     */
    public List<Item> get(Specification<Item> spec, Sort sort) {
        return repositoryItem.findAll(spec, sort);
    }

    /**
     * create element.
     *
     * @param items element to create
     * @return element after creation
     * //     * @throws CreateWithIdException   Exception lancée lors de la création d'un objet existant
     * @throws EntityNotFoundException Exception lors de récupération de l'entité en base
     */
    public Item create(Item items) {
        return save(items);
    }

    /**
     * update element
     *
     * @param id   element identifier
     * @param item element to update
     * @return element after update
     * @throws EntityNotFoundException Exception when retrieve entity
     */
    public Item update(Long id, Item item) {
        if (item.getId() == null) {
            throw new RuntimeException("Can not update entity, entity without ID.");
        } else if (!id.equals(item.getId())) {
            throw new RuntimeException(String.format("Can not update entity, the resource ID (%d) not match the objet ID (%d).", id, item.getId()));
        }
        return save(item);
    }

    /**
     * create \ update elements
     *
     * @param item element to save
     * @return element after save
     */
    protected Item save(Item item) {
        return repositoryItem.save(item);
    }


    @Async
    public List<Item> uploadFile(MultipartFile multipartFile) throws IOException {
        List<Item> itemList = parseCsvFile(multipartFile);
        return (List<Item>) repositoryItem.saveAll(itemList);
    }
}
