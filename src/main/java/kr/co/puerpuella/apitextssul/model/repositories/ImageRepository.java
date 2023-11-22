package kr.co.puerpuella.apitextssul.model.repositories;

import kr.co.puerpuella.apitextssul.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByImageIdAndImageOriginalName(int imageId, String imageOriginalName);
}
