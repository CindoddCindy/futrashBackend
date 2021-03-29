package futrashapi.futrashapiproject.flow_handle.services;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;

import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.ItemImage;
import futrashapi.futrashapiproject.flow_handle.repository.ItemImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@Service
public class ItemImageService {

    @Autowired
    private ItemImageRepository itemImageRepository;

    public ItemImage store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ItemImage itemImage = new ItemImage(fileName, file.getContentType(), file.getBytes());

        return itemImageRepository.save(itemImage);
    }





    public ItemImage getFile(String id) {
        return itemImageRepository.findById(id).get();
    }



    public Stream<ItemImage> getAllFiles() {
        return itemImageRepository.findAll().stream();
    }


    public Stream<ItemImage> getFilesId(String id) {
        return itemImageRepository.findAll().stream();
    }

    //delete image test

    public ResponseEntity<Object> deleteImage(String id) {
        if (itemImageRepository.findById(id).isPresent()) {
            itemImageRepository.deleteById(id);
            if (itemImageRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }

    //edit image test
    public ItemImage storeEdit(String id, MultipartFile file) throws IOException {
        if (itemImageRepository.findById(id).isPresent()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            ItemImage itemImage = new ItemImage(fileName, file.getContentType(), file.getBytes());
            return itemImageRepository.save(itemImage);


        }
        return null;
    }





}
