package com.green.gallery_jwt_jpa.order;


import com.green.gallery_jwt_jpa.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto dto);
    List<OrderGetRes> findAllByMemberIdOrderByIdDesc(int memberId);
    OrderDetailGetRes findByOrderIdAndMemberId(OrderDetailGetReq req);
}