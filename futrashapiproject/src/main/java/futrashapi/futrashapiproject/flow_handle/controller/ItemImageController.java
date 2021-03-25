package futrashapi.futrashapiproject.flow_handle.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseItem;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseMessage;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.ItemImage;
import futrashapi.futrashapiproject.flow_handle.services.ItemImageService;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@Controller
@RequestMapping("/api/futrash/item/image")
public class ItemImageController {

    @Autowired
    private ItemImageService itemImageService;

    @PostMapping("/upload")

    public ResponseEntity<ResponseMessage> uploadImageItem(@RequestParam("file") MultipartFile file) {
        String message = "";
        ItemImage itemImage = new ItemImage();
        try {
            itemImageService.store(file);

            message = itemImageService.store(file).getId();
            ;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }





    @GetMapping("/files/")
    public ResponseEntity<List<ResponseItem>> getListImageItem() {
        List<ResponseItem> files = itemImageService.getAllFiles().map(itemImage -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(itemImage.getId())
                    .toUriString();

            return new ResponseItem(
                    itemImage.getName(),
                    fileDownloadUri,
                    itemImage.getType(),
                    itemImage.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @GetMapping("/files/{id}")
    public ResponseEntity<List<ResponseItem>> getImageItemId() {
        List<ResponseItem> files = itemImageService.getAllFiles().map(itemImage -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(itemImage.getId())
                    .toUriString();

            return new ResponseItem(
                    itemImage.getName(),
                    fileDownloadUri,
                    itemImage.getType(),
                    itemImage.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }








    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFileImageItem(@PathVariable String id) {
        ItemImage fileDB = itemImageService.getFile(id);


        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"" )
                .body(fileDB.getData());




    }


}