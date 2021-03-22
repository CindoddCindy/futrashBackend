package futrashapi.futrashapiproject.flow_handle.repository;

import futrashapi.futrashapiproject.flow_handle.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserImageRepository extends JpaRepository<UserImage, String> {
}
