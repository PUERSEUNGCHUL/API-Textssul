package kr.co.puerpuella.apitextssul.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * 테이블 공통 항목
 */
@MappedSuperclass
@Data
public class LabelEntity implements Serializable {

    /** 작성자 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATE_UID")
    private Member createUser;

    /** 작성일자 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DT")
    @CreatedDate
    private Date createDate;

    /** 작성 요청 IP */
    @Column(name = "CREATE_IP")
    private String createIP;

    /** 갱신자 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATE_UID")
    private Member updateUser;

    /** 갱신일자 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DT")
    @LastModifiedDate
    private Date updateDate;

    /** 갱신 요청 IP */
    @Column(name = "UPDATE_IP")
    private String updateIP;

}
