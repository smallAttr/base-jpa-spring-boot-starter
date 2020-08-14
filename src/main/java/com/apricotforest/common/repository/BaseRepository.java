package com.apricotforest.common.repository;

import com.apricotforest.common.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;

import java.io.*;

/**
 * @author smallAttr
 * @since 2020-04-13 11:49
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
