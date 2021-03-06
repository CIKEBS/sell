package com.allknows.sell.repository;

import com.allknows.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
  @Autowired
  private OrderDetailRepository orderDetailRepository;
  @Test
  public void saveTest(){
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId("1234567810");
    orderDetail.setOrderId("1234567");
    orderDetail.setProductIcon("http://xxxxxx.ico");
    orderDetail.setProductId("111111111111111");
    orderDetail.setProductName("皮蛋粥");
    orderDetail.setProductPrice(new BigDecimal(2.2));
    orderDetail.setProductQuantity(2);

    OrderDetail detail = orderDetailRepository.save(orderDetail);
    Assert.assertNotNull(detail);
  }

  @Test
  public void findByOrderId() {
    List<OrderDetail> byOrderId = orderDetailRepository.findByOrderId("1234567");
    Assert.assertNotEquals(0,byOrderId.size());
  }
}