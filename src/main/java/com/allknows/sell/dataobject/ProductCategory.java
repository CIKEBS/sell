package com.allknows.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "category_info")
@Entity
@DynamicUpdate//动态更新，对于动态更新字段不显式赋值的时候进行动态更新
@Data
public class ProductCategory {
    /**
     * 类目id
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer categoryId;
    /**
     * 类目名字
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;
//    /**
//     *
//     */
//    private Date create_time;
//    /**
//     * 动态更新（数据库字段设置-ON UPDATE CURRENT_TIMESTAMP）
//     */
//    private Date update_time;


    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
