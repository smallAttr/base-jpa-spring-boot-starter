package com.apricotforest.common;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.*;

import javax.persistence.EntityManager;

/**
 * @author smallAttr
 * @since 2020-08-07 14:34
 */
@Configuration
public class AutoQueryDSLConfiguration {

    /**
     * 支持QueryDSL查询方式
     * @param entityManager
     * @return
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
