package com.green.gallery_jwt_jpa.order;

import jakarta.servlet.http.HttpServletRequest;
import com.green.gallery_jwt_jpa.account.etc.AccountConstants;
import com.green.gallery_jwt_jpa.config.util.HttpUtils;
import com.green.gallery_jwt_jpa.order.model.OrderDetailGetReq;
import com.green.gallery_jwt_jpa.order.model.OrderDetailGetRes;
import com.green.gallery_jwt_jpa.order.model.OrderGetRes;
import com.green.gallery_jwt_jpa.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    ResponseEntity<?> add(HttpServletRequest httpReq, @RequestBody OrderPostReq p) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        log.info("p: {}", p);
        int result = orderService.saveOrder(p, logginedMemberId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<?> findAllByMemberIdOrderByIdDesc(HttpServletRequest httpReq) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        List<OrderGetRes> result = orderService.findAllByMemberId(logginedMemberId);
        return ResponseEntity.ok(result);
    }

    //단일용
    @GetMapping("/{orderId}")
    public ResponseEntity<?> findDetail(HttpServletRequest httpReq, @PathVariable int orderId) {
        int logginedMemberId = (int) HttpUtils.getSessionValue(httpReq, AccountConstants.MEMBER_ID_NAME);
        OrderDetailGetReq req = new OrderDetailGetReq();
        req.setOrderId(orderId);
        req.setMemberId(logginedMemberId);
        OrderDetailGetRes result = orderService.detail(req);
        return ResponseEntity.ok(result);
    }
}
