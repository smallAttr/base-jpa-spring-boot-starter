package com.apricotforest.common;

import com.apricotforest.common.entity.*;
import com.apricotforest.common.repository.impl.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.support.*;
import org.springframework.data.repository.core.*;
import org.springframework.data.repository.core.support.*;

import javax.persistence.EntityManager;
import java.io.*;

/**
 * @author smallAttr
 * @since 2020-04-13 14:26
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, Serializable>, T>  extends JpaRepositoryFactoryBean<R, T, Serializable> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {

            @Override
            protected SimpleJpaRepository<T, Serializable> getTargetRepository(
                    RepositoryInformation information, EntityManager entityManager) {
                Class<T> domainClass = (Class<T>) information.getDomainType();
                if(BaseEntity.class.isAssignableFrom(domainClass)) {
                    return new BaseRepositoryImpl(domainClass, entityManager);
                } else {
                    return new SimpleJpaRepository(domainClass, entityManager);
                }
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return metadata.getDomainType().isAssignableFrom(BaseEntity.class) ? BaseRepositoryImpl.class : SimpleJpaRepository.class;
            }
        };
    }


}
