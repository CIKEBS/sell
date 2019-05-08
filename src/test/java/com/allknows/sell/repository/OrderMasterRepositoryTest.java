package com.allknows.sell.repository;

import com.allknows.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPEN_ID = "110110";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerIphone("11111111111");
        orderMaster.setBuyerAddress("沙特");
        orderMaster.setBuyerOpenid(OPEN_ID);
        orderMaster.setOrderAmount(new BigDecimal(2.5d));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByBuyerOpenid(){
        PageRequest request = PageRequest.of(0,3);
        Page<OrderMaster> byBuyerOpenid = orderMasterRepository.findByBuyerOpenid(OPEN_ID, request);
        Assert.assertNotEquals(0,byBuyerOpenid.getTotalElements());
    }
}