package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_IMAGE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends LabelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Integer imageId;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "IMAGE_NM_ORIGIN")
    private String imageOriginalName;

    @Column(name = "IMAGE_NM")
    private String imageName;

    @Column(name = "IMAGE_EXT")
    private String imageExtension;

    @Column(name = "IMAGE_SIZE")
    private Long imageSize;


    @Override
    public int hashCode() {
        return this.imageId;
    }
}