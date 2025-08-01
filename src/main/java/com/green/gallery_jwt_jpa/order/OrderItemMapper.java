package com.green.gallery_jwt_jpa.order;

import com.green.gallery_jwt_jpa.order.model.OrderDetailDto;
import com.green.gallery_jwt_jpa.order.model.OrderItemPostDto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    // 주문상품 등록
    int save(OrderItemPostDto dto);
    // 주문번호로 주문상품 목록 조회
    List<OrderDetailDto> findAllByOrderId(int orderId);
}
