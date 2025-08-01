package com.green.gallery_jwt_jpa.order;

import com.green.gallery_jwt_jpa.cart.CartMapper;
import com.green.gallery_jwt_jpa.item.ItemMapper;
import com.green.gallery_jwt_jpa.item.model.ItemGetRes;
import com.green.gallery_jwt_jpa.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

        @Slf4j
        @Service
        @RequiredArgsConstructor
        public class OrderService {
            private final OrderMapper orderMapper;
            private final ItemMapper itemMapper;
            private final OrderItemMapper orderItemMapper;
            private final CartMapper cartMapper;

            @Transactional
            public int saveOrder(OrderPostReq p, int logginedMemberId) {
                // 1. 상품 정보 조회
                List<ItemGetRes> itemList = itemMapper.findAllByIdIn(p.getItemIds());

                // 2. 총 금액 계산
                long total=0;
                for (ItemGetRes item : itemList) {
                    total += item.getPrice() - (item.getPrice() * item.getDiscountPer() / 100.0);
                    // 수량 1개로 계산
                }
                log.info("total = {}원",  total);    //총 구매가격 콘솔에 출력

                // 3. OrderPostDto 객체 생성 (빌더 패턴)
                OrderPostDto dto = OrderPostDto.builder()
                        .memberId(logginedMemberId)
                        .name(p.getName())
                        .address(p.getAddress())
                        .payment(p.getPayment())
                        .cardNumber(p.getCardNumber())
                        .amount(total)
                        .build();

                log.info("before save dto = {}", dto);

                // 4. 주문 저장
                orderMapper.save(dto);
                log.info("after save dto = {}", dto);

                // 주문 아이템 DTO 생성
                OrderItemPostDto orderItemPostDto = new OrderItemPostDto(dto.getOrderId(), p.getItemIds());

                //상품리스트 등록
                orderItemMapper.save(orderItemPostDto);

                // 주문 완료 후 장바구니 비우기
                cartMapper.deleteAllCart(logginedMemberId);
                return 1;
            }

            public List<OrderGetRes> findAllByMemberId(int memberId) {
                return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
            }

            public OrderDetailGetRes detail(OrderDetailGetReq p) {
                OrderDetailGetRes result = orderMapper.findByIdAndMemberId(p);
                List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(p.getOrderId());
                result.setItems(items);
                log.info("result={}", result);
                return result;
            }
        }
