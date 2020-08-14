package com.apricotforest.common.entity;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.*;
import java.io.*;
import java.time.*;

/**
 * @author smallAttr
 * @since 2020-03-17 19:23
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7988377299341530426L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Integer id;

    @CreatedDate
    @Column(columnDefinition = "datetime not null DEFAULT CURRENT_TIMESTAMP comment '创建时间'")
    private LocalDateTime createTime = LocalDateTime.now();

    @LastModifiedDate
    @Column(columnDefinition = "datetime not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间时间'")
    private LocalDateTime updateTime = LocalDateTime.now();

    @Column(columnDefinition = "tinyint(1) not null default 0 comment '是否删除 1-已删除 0-未删除'")
    private boolean deleted;

    @Version
    @Column(columnDefinition = "bigint not null default 0 comment '版本号'")
    private Long version;

}
