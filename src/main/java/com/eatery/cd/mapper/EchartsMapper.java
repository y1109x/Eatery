package com.eatery.cd.mapper;

import com.eatery.cd.pojo.OrderAddCount;
import com.eatery.cd.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EchartsMapper {
    List<Product> findAll();
    List<OrderAddCount> findAllAddr();
}
