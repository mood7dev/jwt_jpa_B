package com.green.gallery_jwt_jpa.order;

import com.green.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import com.green.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import com.green.gallery_jwt_jpa.order.model.OrderGetRes;
import com.green.gallery_jwt_jpa.order.model.OrderPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto dto);
    List<OrderGetRes> findAllByMemberIdOrderByIdDesc(int memberId);
    OrderDetailGetRes findByIdAndMemberId(OrderDetailGetReq req);
}