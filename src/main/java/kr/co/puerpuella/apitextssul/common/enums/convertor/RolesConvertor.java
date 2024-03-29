package kr.co.puerpuella.apitextssul.common.enums.convertor;

import jakarta.persistence.AttributeConverter;
import kr.co.puerpuella.apitextssul.common.enums.Roles;


public class RolesConvertor implements AttributeConverter<Roles, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Roles attribute) {
        return attribute.getRoleKey();
    }

    @Override
    public Roles convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Roles.valueOf(dbData);
    }
}
