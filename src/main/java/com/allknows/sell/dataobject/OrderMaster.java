package com.allknows.sell.dataobject;

import com.allknows.sell.enums.OrderStatusEnum;
import com.allknows.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/** 订单主表 */
@Table(name = "order_master")
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
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
  private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**
     * 支付状态，默认0未支付
     */
  private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /**
     * 创建时间
     */
  private Timestamp createTime;
    /**
     * 更新时间
     */
  private Timestamp updateTime;


}