package com.wusw.cloud.mapper;

import com.wusw.cloud.entities.Storage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}