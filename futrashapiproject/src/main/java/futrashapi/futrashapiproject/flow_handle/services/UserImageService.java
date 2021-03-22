package futrashapi.futrashapiproject.flow_handle.services;


import java.io.IOException;
import java.util.stream.Stream;

import futrashapi.futrashapiproject.flow_handle.model.UserImage;
import futrashapi.futrashapiproject.flow_handle.repository.UserImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserImageService {

    @Autowired
    private UserImageRepository userImageRepository;

    public UserImage store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UserImage FileDB = new UserImage(fileName, file.getContentType(), file.getBytes());

        return userImageRepository.save(FileDB);
    }

    public UserImage getFile(String id) {
        return userImageRepository.findById(id).get();
    }

    public Stream<UserImage> getAllFiles() {
        return userImageRepository.findAll().stream();
    }
}
