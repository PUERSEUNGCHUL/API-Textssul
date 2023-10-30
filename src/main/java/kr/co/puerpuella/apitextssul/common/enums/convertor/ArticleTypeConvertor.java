package kr.co.puerpuella.apitextssul.common.enums.convertor;

import jakarta.persistence.AttributeConverter;
import kr.co.puerpuella.apitextssul.common.enums.ArticleType;
import kr.co.puerpuella.apitextssul.common.enums.Roles;


public class ArticleTypeConvertor implements AttributeConverter<ArticleType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleType attribute) {
        return attribute.getTypeId();
    }

    @Override
    public ArticleType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return ArticleType.valueOf(dbData);
    }
}
