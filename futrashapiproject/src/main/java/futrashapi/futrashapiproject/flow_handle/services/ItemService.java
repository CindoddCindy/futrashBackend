package futrashapi.futrashapiproject.flow_handle.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import futrashapi.futrashapiproject.auth.model.User;
import futrashapi.futrashapiproject.flow_handle.model.Item;
import futrashapi.futrashapiproject.flow_handle.model.UserImage;
import futrashapi.futrashapiproject.flow_handle.repository.ItemRepository;
import futrashapi.futrashapiproject.flow_handle.repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item store(MultipartFile file, String jenis_makanan, String tidak_dikonsumsi_sejak,
                      String dijual_karena, String berat_makanan, String nama_toko, String nama_penjual,
                      String lokasi_makanan, String harga_makanan, String saran_penggunaan, String kandungan_kimia
                      ) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Item FileDB = new Item(fileName, file.getContentType(), file.getBytes(), jenis_makanan,
                tidak_dikonsumsi_sejak,dijual_karena,berat_makanan,nama_toko,
                nama_penjual,lokasi_makanan,harga_makanan,saran_penggunaan,kandungan_kimia
                );

        return itemRepository.save(FileDB);
    }

    public Item getFile(String id) {
        return itemRepository.findById(id).get();
    }

    public Stream<Item> getAllFiles() {
        return itemRepository.findAll().stream();
    }
}
