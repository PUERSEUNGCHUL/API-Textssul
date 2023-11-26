package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_IMAGE")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image extends LabelEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID")
    @Setter
    private Article article;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder;

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