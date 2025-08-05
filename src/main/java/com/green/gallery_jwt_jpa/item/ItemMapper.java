package com.green.gallery_jwt_jpa.item;


import com.green.gallery_jwt_jpa.item.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int save(ItemPostReq req);
    List<ItemGetRes> findAllByIdIn(List<Integer> ids);
}