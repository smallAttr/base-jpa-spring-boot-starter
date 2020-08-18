package com.apricotforest.common.factory;

import com.apricotforest.common.enums.*;
import org.springframework.core.convert.converter.*;

/**
 * @author smallAttr
 * @since 2020-08-18 13:17
 */
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> aClass) {
        if (!aClass.isEnum()) {
            throw new UnsupportedOperationException("只支持转换到枚举类型");
        }
        return new StringToBaseEnumConverter<>(aClass);
    }

    private class StringToBaseEnumConverter<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public StringToBaseEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String s) {
            for (T t : enumType.getEnumConstants()) {
                if (s.toUpperCase().equals(t.getName())) {
                    return t;
                }
            }
            throw new IllegalArgumentException("No element matches " + s);
        }
    }
}
