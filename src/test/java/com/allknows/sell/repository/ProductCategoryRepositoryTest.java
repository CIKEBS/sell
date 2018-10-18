package com.allknows.sell.repository;

import com.allknows.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOne(){
        try {
            Optional<ProductCategory> category = repository.findById(2);
//            ProductCategory category = repository.findById(2).get();
            System.out.println(category.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    @Transactional //回滚事务，避免测试数据入库
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("男生最爱",4);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);
    }
    @Test
    public void updateTest(){
        Optional<ProductCategory> category = repository.findById(2);
        ProductCategory productCategory = category.get();
        productCategory.setCategoryType(5);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3, 5);
        List<ProductCategory> byCategoryTypeIn = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

}