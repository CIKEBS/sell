package com.allknows.sell.service.impl;

import com.allknows.sell.dataobject.OrderDetail;
import com.allknows.sell.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
  private final String BUYER_OPENID = "1101110";
  private final String ORDER_ID = "1552374429405637282";
  @Autowired
  private OrderServiceImpl orderService;
  @Test
  public void create() {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName("张三");
    orderDTO.setBuyerAddress("王府井");
    orderDTO.setBuyerIphone("12345678910");
    orderDTO.setBuyerOpenid(BUYER_OPENID);

    //购物车
    List<OrderDetail> orderDetails = new ArrayList<>();
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setProductId("123456");
    orderDetail.setProductQuantity(1);
    orderDetails.add(orderDetail);

    OrderDetail orderDetail2 = new OrderDetail();
    orderDetail2.setProductId("123457");
    orderDetail2.setProductQuantity(5);
    orderDetails.add(orderDetail2);

    orderDTO.setOrderDetailList(orderDetails);
    OrderDTO dto = orderService.create(orderDTO);
    log.info("【创建订单】 result={}",dto);
  }

  @Test
  public void findOne() {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    log.info("【查询单个订单】 result={}",orderDTO);
  }

  @Test
  public void findList() {
    PageRequest request = PageRequest.of(0,2);
    Page<OrderDTO> list = orderService.findList(BUYER_OPENID, request);
    Assert.assertNotEquals(0,list.getTotalElements());
  }

  @Test
  public void cancel() {}

  @Test
  public void finish() {}

  @Test
  public void paid() {}
}