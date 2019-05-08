package com.allknows.sell.dto;

import com.allknows.sell.dataobject.OrderDetail;
import com.allknows.sell.enums.OrderStatusEnum;
import com.allknows.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@Data
public class OrderDTO {
    /**
     * 订单ID
     */
    @Id
    private String orderId;
    /**
     * 买家名称
     */
    private  String buyerName;
    /**
     * 买家电话
     */
    private String buyerIphone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认0新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态，默认0未支付
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;

    private List<OrderDetail> orderDetailList;
}
