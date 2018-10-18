package com.allknows.sell.repository;

import com.allknows.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategory_name("女生最爱");
        productCategory.setCategory_type(3);
        repository.save(productCategory);
    }
}