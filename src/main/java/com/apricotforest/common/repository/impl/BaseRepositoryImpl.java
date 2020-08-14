package com.apricotforest.common.repository.impl;

import com.apricotforest.common.entity.*;
import com.apricotforest.common.repository.*;
import org.springframework.data.jpa.repository.support.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.*;

/**
 * @author smallAttr
 * @since 2020-04-13 11:56
 */
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    @SuppressWarnings("unused")
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, Serializable> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    /**
     * 只做逻辑删除
     */
    @Override
    public void delete(T entity) {
        entity.setDeleted(true);
        this.save(entity);
    }

    /**
     * 只做逻辑删除
     */
    @Override
    public void deleteById(ID id) {
        T entity = findById(id).orElseThrow(() -> new EntityNotFoundException("未找到对应记录"));
        entity.setDeleted(true);
        this.save(entity);
    }
}
