package kr.co.puerpuella.apitextssul.common.enums.convertor;

import jakarta.persistence.AttributeConverter;
import kr.co.puerpuella.apitextssul.common.enums.ArticleCategory;
import kr.co.puerpuella.apitextssul.common.enums.Roles;


public class ArticleCategoryConvertor implements AttributeConverter<ArticleCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ArticleCategory attribute) {
        return attribute.getCategoryId();
    }

    @Override
    public ArticleCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return ArticleCategory.valueOf(dbData);
    }
}
