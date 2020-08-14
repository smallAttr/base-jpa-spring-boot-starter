package com.apricotforest.common.convert;

import com.apricotforest.common.enums.*;
import org.hibernate.*;
import org.hibernate.engine.spi.*;
import org.hibernate.usertype.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * @author smallAttr
 * @since 2020-04-24 14:00
 */
public class EnumType implements UserType, DynamicParameterizedType {

    private Class enumClass;
    private static final int[] SQL_TYPES = new int[]{Types.INTEGER};

    @Override
    public void setParameterValues(Properties parameters) {
        final ParameterType reader = (ParameterType) parameters.get(PARAMETER_TYPE);
        if (Objects.nonNull(reader)) {
            enumClass = reader.getReturnedClass().asSubclass(Enum.class);
        }
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return enumClass;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (Objects.isNull(x) && Objects.isNull(y)) {
            return true;
        }
        if ((Objects.isNull(x) && Objects.nonNull(y)) ||
                (Objects.nonNull(x) && Objects.isNull(y))) {
            return false;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.isNull(x) ? 0 : x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        String value = rs.getString(names[0]);
        if (value == null) {
            return null;
        }
        for (Object object : enumClass.getEnumConstants()) {
            if (Objects.equals(Integer.parseInt(value), ((BaseEnum) object).getValue())) {
                return object;
            }
        }
        throw new RuntimeException(String.format("Unknown name value [%s] for enum class [%s]", value, enumClass.getName()));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, SQL_TYPES[0]);
        } else if (value instanceof Integer) {
            st.setInt(index, (Integer) value);
        } else {
            st.setInt(index, ((BaseEnum) value).getValue());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
