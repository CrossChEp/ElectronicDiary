package com.diary.diary.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class ListToStingConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if(attribute == null) {
            return null;
        }
        StringBuilder resString = new StringBuilder();
        for(var string: attribute) {
            resString.append(string);
            resString.append(" ");
        }
        return resString.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return Arrays.asList(dbData.split(" "));
    }
}
