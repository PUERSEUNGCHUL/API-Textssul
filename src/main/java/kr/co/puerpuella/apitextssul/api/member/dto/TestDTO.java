package kr.co.puerpuella.apitextssul.api.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestDTO {

    private String id;

    private String password;

    private String content;
}
