package com.apricotforest.common;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

import javax.persistence.EntityManager;

/**
 * @author smallAttr
 * @since 2020-08-07 14:34
 */
@Configuration
@ConditionalOnBean(value = EntityManager.class)
public class AutoQueryDSLConfiguration {

    /**
     * 支持QueryDSL查询方式
     * @param entityManager
     * @return
     */
    @ConditionalOnMissingBean
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
