package com.green.gallery_jwt_jpa.order;

import com.green.gallery_jwt_jpa.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int save(OrderItemPostDto dto);
    List<OrderDetailDto> findAllByOrderId(int orderId);
}