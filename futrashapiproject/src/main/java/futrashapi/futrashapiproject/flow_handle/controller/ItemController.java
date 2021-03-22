package futrashapi.futrashapiproject.flow_handle.controller;


import java.util.List;
import java.util.stream.Collectors;

import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseItem;
import futrashapi.futrashapiproject.flow_handle.exception.message.ResponseMessage;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


@Controller
//@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/futrash/items")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @Param("jenis_makanan")String jenis_makanan,
                                                      @Param("tidak_dikonsumsi_sejak") String tidak_dikonsumsi_sejak,
                                                      @Param("dujual_karena") String dijual_karena,
                                                      @Param("berat_makanan") String berat_makanan,
                                                      @Param("nama_toko") String nama_toko,
                                                      @Param("nama_penjual") String nama_penjual,
                                                      @Param("lokasi_makanan") String lokasi_makanan,
                                                      @Param("harga_makanan") String harga_makanan,
                                                      @Param("saran_penggunaan") String saran_penggunaan,
                                                      @Param("kandungan_kimia") String kandungan_kimia,
                                                      @RequestPart(name = "users") @Valid final List<User> users) {
        String message = "";
        try {
            itemService.store(file,jenis_makanan,tidak_dikonsumsi_sejak,dijual_karena,berat_makanan,nama_toko,nama_penjual,lokasi_makanan,harga_makanan,saran_penggunaan,kandungan_kimia,users);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }





    @GetMapping("/files")
    public ResponseEntity<List<ResponseItem>> getListFiles() {
        List<ResponseItem> files = itemService.getAllFiles().map(item -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(item.getId())
                    .toUriString();

            return new ResponseItem(
                    item.getName(),
                    fileDownloadUri,
                    item.getType(),
                    item.getData().length);



        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Item fileDB = itemService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
