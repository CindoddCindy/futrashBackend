package futrashapi.futrashapiproject.flow_handle.controller;


import java.util.List;
import java.util.stream.Collectors;

import futrashapi.futrashapiproject.flow_handle.exception.message.ResponUserImage;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseMessageUserImage;
import futrashapi.futrashapiproject.flow_handle.model.UserImage;
import futrashapi.futrashapiproject.flow_handle.services.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@Controller
//@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/foodTrash/user/image")
public class UserImageController {

    @Autowired
    private UserImageService userImageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessageUserImage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            userImageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessageUserImage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessageUserImage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponUserImage>> getListFiles() {
        List<ResponUserImage> files = userImageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponUserImage(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        UserImage fileDB = userImageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
