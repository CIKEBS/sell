package com.allknows.sell.service.impl;

import com.allknows.sell.Exception.SellException;
import com.allknows.sell.converter.OrderMaster2OrderDTOConverter;
import com.allknows.sell.dataobject.OrderDetail;
import com.allknows.sell.dataobject.OrderMaster;
import com.allknows.sell.dataobject.ProductInfo;
import com.allknows.sell.dto.CartDTO;
import com.allknows.sell.dto.OrderDTO;
import com.allknows.sell.enums.OrderStatusEnum;
import com.allknows.sell.enums.PayStatusEnum;
import com.allknows.sell.enums.ResultEnum;
import com.allknows.sell.repository.OrderDetailRepository;
import com.allknows.sell.repository.OrderMasterRepository;
import com.allknows.sell.service.OrderService;
import com.allknows.sell.service.ProductService;
import com.allknows.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品 （数量，价格）
        for (OrderDetail detail : orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(detail.getProductId());
            if (productInfo == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(orderAmount);
            //3.订单详情入库
            detail.setDetailId(KeyUtil.genUniqueKey());
            detail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,detail);
            orderDetailRepository.save(detail);
        }
        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.更新库存
        List<CartDTO> cartDTOS = new ArrayList<CartDTO>();
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderID) {
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderID);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderID);
        if (orderDetails == null ){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster.get(),orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
