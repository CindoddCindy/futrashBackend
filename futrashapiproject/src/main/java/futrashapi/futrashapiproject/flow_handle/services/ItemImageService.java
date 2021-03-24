package futrashapi.futrashapiproject.flow_handle.services;

import java.io.IOException;
import java.util.stream.Stream;

import futrashapi.futrashapiproject.flow_handle.model.ItemImage;
import futrashapi.futrashapiproject.flow_handle.repository.ItemImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


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
}
