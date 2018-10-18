package com.allknows.sell.dataobject;

import javax.persistence.*;
import java.util.Date;

@Table(name = "category_info")
@Entity
public class ProductCategory {
    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    /**
     * 类目名字
     */
    private String category_name;
    /**
     * 类目编号
     */
    private Integer category_type;
//    /**
//     *
//     */
//    private Date create_time;
//    /**
//     *
//     */
//    private Date update_time;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getCategory_type() {
        return category_type;
    }

    public void setCategory_type(Integer category_type) {
        this.category_type = category_type;
    }

//    public Date getCreate_time() {
//        return create_time;
//    }
//
//    public void setCreate_time(Date create_time) {
//        this.create_time = create_time;
//    }
//
//    public Date getUpdate_time() {
//        return update_time;
//    }
//
//    public void setUpdate_time(Date update_time) {
//        this.update_time = update_time;
//    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "category_info=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", category_type=" + category_type +
//                ", create_time=" + create_time +
//                ", update_time=" + update_time +
                '}';
    }
}
