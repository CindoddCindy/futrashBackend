package futrashapi.futrashapiproject.api_search.controller;


import futrashapi.futrashapiproject.api_search.entity.utils.PagingHeaders;
import futrashapi.futrashapiproject.api_search.entity.utils.PagingResponse;
import futrashapi.futrashapiproject.api_search.service.ItemService;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.pivovarit.function.ThrowingFunction.unchecked;


@Slf4j
@RestController
@RequestMapping("/api/foodTrash/search")
public class ControllerItem {


    private final ItemService itemService;

    @Autowired
    public ControllerItem(ItemService itemService) {
        this.itemService = itemService;
    }

    @Transactional
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }

    @Transactional
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Item update(@PathVariable(name = "id") Long id, @RequestBody Item item) {
        return itemService.update(id, item);
    }

    @Transactional
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) {
        itemService.delete(id);
    }

    @Transactional
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Item get(@PathVariable(name = "id") Long id) {
        return itemService.get(id);
    }

    @Transactional
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Item>> get(
            @And({
                    @Spec(path = "jenis_makanan", params = "jenis_makanan", spec = Like.class),
                    @Spec(path = "lokasi_makanan", params = "lokasi_makanan", spec = Like.class),
                    @Spec(path = "nama_toko", params = "nama_toko", spec = In.class),
                    @Spec(path = "nama_penjual", params = "nama_penjual", spec = Like.class),
                    @Spec(path = "createDate", params = "createDate", spec = Equal.class),
                    @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
            }) Specification<Item> spec,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        final PagingResponse response = itemService.get(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }

    @ResponseBody
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<Item>> uploadFile(@RequestParam(value = "files") List<MultipartFile> files) {
        List<Item> cars = files.stream()
                .parallel()
                .map(unchecked(itemService::uploadFile))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cars);
    }

    @GetMapping(value = "/extract", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Resource> extractFile(
            @And({
                    @Spec(path = "manufacturer", params = "manufacturer", spec = Like.class),
                    @Spec(path = "model", params = "model", spec = Like.class),
                    @Spec(path = "country", params = "country", spec = In.class),
                    @Spec(path = "type", params = "type", spec = Like.class),
                    @Spec(path = "createDate", params = "createDate", spec = Equal.class),
                    @Spec(path = "createDate", params = {"createDateGt", "createDateLt"}, spec = Between.class)
            }) Specification<Item> spec,
            Sort sort) throws IOException {
        List<Item> cars = itemService.get(spec, sort);
        Resource resource = itemService.generateCsvFile(cars);

        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Search_Extraction_" + now + ".csv\"")
                .body(resource);
    }

}
