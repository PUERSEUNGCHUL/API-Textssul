package kr.co.puerpuella.apitextssul.common.enums.convertor;

import jakarta.persistence.AttributeConverter;

public class BooleanConvertor implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute? "1" : "0";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "1".equals(dbData);
    }

    public static boolean resolve(Integer data) {
        return data != null && data == 1;
    }
}
